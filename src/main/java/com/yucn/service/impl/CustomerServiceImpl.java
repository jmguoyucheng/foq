package com.yucn.service.impl;

import com.yucn.constant.CronConstant;
import com.yucn.converter.Customer2CustomerFeeDetail;
import com.yucn.enums.DetailTypeEnum;
import com.yucn.enums.ResultEnum;
import com.yucn.exception.FoqException;
import com.yucn.entity.*;
import com.yucn.repository.*;
import com.yucn.service.CustomerService;
import com.yucn.utils.ConvertUtil;
import com.yucn.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2018/2/5.
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SaleStrategyRepository saleStrategyRepository;
    @Autowired
    private SaleCategoryRepository saleCategoryRepository;
    @Autowired
    private CustomerFeeDetailRepository customerFeeDetailRepository;

    @Override
    public Customer add(Customer customerDto) {
        sort(1);
        //创建Customer对象
        Customer customer = new Customer();
        //复制属性,忽略saleStrategies
        BeanUtils.copyProperties(customerDto, customer, "saleStrategies");
        //设置排序号
        customer.setOrderNum(1);
        //充值额度即为余额
        customer.setBalance(customer.getRecharge());

        customerDto.getSaleStrategies().forEach(saleStrategy -> {
            //查询对应的SaleCategory
            SaleCategory saleCategory = saleCategoryRepository.findOne(saleStrategy.getSaleCategory().getId());
            //SaleStrategy到SaleCategory的关系
            saleStrategy.setSaleCategory(saleCategory);
            //SaleStrategy到Customer的关系
            saleStrategy.setCustomer(customer);
            //Customer到SaleStrategy的关系
            customer.getSaleStrategies().add(saleStrategy);
        });
        //级联保存customer
        customerRepository.save(customer);
        // 充值金额不为0时，记录到充值明细记录表
        if (!customer.getRecharge().equals(BigDecimal.ZERO)) {
            // 保存到明细记录表
            CustomerFeeDetail customerFeeDetail = Customer2CustomerFeeDetail.convert(customer, "新增客户" + DetailTypeEnum.CUSTOMER_RECHARGE.getMessage());
            customerFeeDetail.setAccurAmmount(customer.getRecharge());
            customerFeeDetail.setType(DetailTypeEnum.CUSTOMER_RECHARGE.getCode());
            customerFeeDetailRepository.save(customerFeeDetail);
        }

        customerRepository.save(customer);
        return customer;
    }

    @Override
    @Transactional
    public Customer modify(Customer customerDto) {
        //如果“排**之前”输入内容非空，则进行排序
        if (customerDto.getSuffixId() != null) {
            Customer suffixCustomer = customerRepository.findByIdAndDeletedFalse(customerDto.getSuffixId());
            //如果能查询到符合条件的记录，则进行排序
            if (suffixCustomer != null) {
                //以下两条语句顺序一定不能颠倒，否则排序出错
                customerDto.setOrderNum(suffixCustomer.getOrderNum());
                sort(suffixCustomer.getOrderNum());
            }
        }

        Customer customer = customerRepository.findOne(customerDto.getId());
        BeanUtils.copyProperties(customerDto, customer, "saleStrategies");
        customer.getSaleStrategies().clear();
        for (SaleStrategy saleStrategyDto : customerDto.getSaleStrategies()) {
            SaleStrategy saleStrategy;
            // 若id不存在，则为新增销售方案
            if (saleStrategyDto.getId() == null) {
                saleStrategy = new SaleStrategy();
            } else {
                saleStrategy = saleStrategyRepository.findOne(saleStrategyDto.getId());
            }
            BeanUtils.copyProperties(saleStrategyDto, saleStrategy);

            //查询对应的SaleCategory
            SaleCategory saleCategory = saleCategoryRepository.findOne(saleStrategy.getSaleCategory().getId());
            //SaleStrategy到SaleCategory的关系
            saleStrategy.setSaleCategory(saleCategory);
            //SaleStrategy到Customer的关系
            saleStrategy.setCustomer(customer);
            //Customer到SaleStrategy的关系
            customer.getSaleStrategies().add(saleStrategy);
        }
        customerRepository.save(customer);

        return customer;
    }


    @Override
    public Customer delete(Long id) {
        Customer customer = customerRepository.findOne(id);
        customer.setDeleted(true);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Page<Customer> findListDeleted(String mode, String key, Pageable pageable) {
        Page<Customer> customerPage = null;
        if (mode.equals("byName")) {
            customerPage = customerRepository.findByNameContainsAndDeletedTrue(key, pageable);
        } else if (mode.equals("byMobile")) {
            customerPage = customerRepository.findByMobileContainsAndDeletedTrue(key, pageable);
        } else {
            customerPage = customerRepository.findByAddressContainsAndDeletedTrue(key, pageable);
        }
        return customerPage;
    }

    @Override
    public Page<Customer> findList(String mode, String key, Pageable pageable) {
        Page<Customer> customerPage = null;
        if (mode.equals("byId")) {
            customerPage = customerRepository.findByIdInAndDeletedFalse(ConvertUtil.string2Longs(key), pageable);
        } else if (mode.equals("byName")) {
            customerPage = customerRepository.findByNameContainsAndDeletedFalse(key, pageable);
        } else if (mode.equals("byMobile")) {
            customerPage = customerRepository.findByMobileContainsAndDeletedFalse(key, pageable);
        } else {
            customerPage = customerRepository.findByAddressContainsAndDeletedFalse(key, pageable);
        }
        // 计算过期日
        customerPage.forEach(customer -> {
            int expireDays = 999999;
            BigDecimal totalPrice = BigDecimal.ZERO;
            //List<SaleStrategy> saleStrategies = new ArrayList<>();
            for (SaleStrategy saleStrategy : customer.getSaleStrategies()) {
                //若当前时间在起始和结束时间内，则计算进总价格
                //若当前时间在起始和结束时间内，则展示销售方案
                if (TimeUtil.between(new Date(),
                        saleStrategy.getStartTime() == null ? new Date() : saleStrategy.getStartTime(),
                        saleStrategy.getEndTime() == null|| (saleStrategy.getStartTime() != null && saleStrategy.getEndTime().before(saleStrategy.getStartTime())) ? new Date() : saleStrategy.getEndTime())) {
                    //if (saleStrategy.getEndTime() != null && saleStrategy.getEndTime().before(new Date())
                    //        || saleStrategy.getStartTime() != null && saleStrategy.getStartTime().after(new Date())) {
                    // 一定不能再此处remove，否则删除一个后就无法循环了，会报空指针
                    //saleStrategies.add(saleStrategy);
                    saleStrategy.setEnable(true);
                    totalPrice = totalPrice.add(saleStrategy.getTotalPrice());
                } else {
                    saleStrategy.setEnable(false);
                }
            }
            //customer.getSaleStrategies().removeAll(saleStrategies);
            if (totalPrice.compareTo(BigDecimal.ZERO) != 0) {
                expireDays = customer.getBalance().divide(totalPrice, RoundingMode.DOWN).intValue();
            }
            customer.setExpireDays(new Integer(expireDays));
        });

        return customerPage;
    }

    @Override
    @Transactional
    public Customer findOne(Long id) {
        Customer customer = customerRepository.findOne(id);
        if (customer == null) {
            throw new FoqException(ResultEnum.CUSTOMER_NOT_EXIST);
        }
        // 去除失效的销售方案
        //List<SaleStrategy> saleStrategies = new ArrayList<>();
        //for (SaleStrategy saleStrategy : customer.getSaleStrategies()) {
        //    if (saleStrategy.getEndTime() != null && saleStrategy.getEndTime().before(new Date())) {
        //        // 一定不能再此处remove，否则删除一个后就无法循环了，会报空指针
        //        saleStrategies.add(saleStrategy);
        //    }
        //}
        //customer.getSaleStrategies().removeAll(saleStrategies);

        return customer;
    }

    @Override
    @Transactional
    public Customer adjustCost(Long id, BigDecimal cost, DetailTypeEnum detailType, String description) {
        Customer customer = customerRepository.findOne(id);
        customer.setBalance(customer.getBalance().add(cost));
        customerRepository.save(customer);

        // 保存到客户明细表
        CustomerFeeDetail customerFeeDetail = Customer2CustomerFeeDetail.convert(customer,
                description == null || description.isEmpty() ? detailType.getMessage() : detailType.getMessage() + ":" + description);
        customerFeeDetail.setAccurAmmount(cost);
        customerFeeDetail.setType(detailType.getCode());
        customerFeeDetailRepository.save(customerFeeDetail);

        return customer;
    }

    @Scheduled(cron = CronConstant.cron + "*")
    @Transactional
    public void charge() throws ParseException {
        Date now = new Date();
        //当前时间往前推10分钟
        Date before = DateUtils.addMinutes(now, -10);

        log.info("送奶扣费");
        // 查询出所有未删除的客户
        List<Customer> customers = customerRepository.findAllByDeletedFalse();
        List<CustomerFeeDetail> customerFeeDetails = new ArrayList<>();
        for (Customer customer : customers) {
            String saleStrategies = "";
            BigDecimal totalPrice = BigDecimal.ZERO;
            // 计算当前用户所有符合送奶条件的销售方案的总价
            for (SaleStrategy saleStrategy : customer.getSaleStrategies()) {
                // 判断当前时间在有效期范围内
                // 如果开始时间为空，则取当前时间
                Date startTime = saleStrategy.getStartTime() == null ? now : saleStrategy.getStartTime();
                // 如果结束时间为空或小于开始时间，则取当前时间
                Date endTime = saleStrategy.getEndTime() == null || (saleStrategy.getStartTime() != null && saleStrategy.getEndTime().before(saleStrategy.getStartTime())) ? now : saleStrategy.getEndTime();
                if (TimeUtil.between(now, startTime, endTime)) {
                    // 判断当前时间是否满足周期表达式
                    String cron = CronConstant.cron + saleStrategy.getCron();
                    CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
                    cronTriggerImpl.setCronExpression(cron);
                    List<Date> dates = TriggerUtils.computeFireTimesBetween(cronTriggerImpl, null, before, now);
                    if (dates.size() > 0) {
                        saleStrategies += saleStrategy.getSaleCategory().getName() + "[" + saleStrategy.getAmount() + "]";
                        totalPrice = totalPrice.add(saleStrategy.getTotalPrice());
                    }
                }
            }
            //如果总价大于0，则保存明细
            if (totalPrice.compareTo(BigDecimal.ZERO) > 0) {
                customer.setBalance(customer.getBalance().subtract(totalPrice));
                CustomerFeeDetail customerFeeDetail = Customer2CustomerFeeDetail.convert(customer, DetailTypeEnum.DEDUCT_EXPENSES.getMessage());
                // 设置销售方案
                customerFeeDetail.setSaleStrategies(saleStrategies);
                //设置发生金额，设置为负数
                customerFeeDetail.setAccurAmmount(BigDecimal.ZERO.subtract(totalPrice));
                //设置明细类型
                customerFeeDetail.setType(DetailTypeEnum.DEDUCT_EXPENSES.getCode());
                customerFeeDetails.add(customerFeeDetail);
            }
        }
        //保存客户信息，因为余额发生变化了
        customerRepository.save(customers);
        //保存明细
        customerFeeDetailRepository.save(customerFeeDetails);
    }

    private void sort(Integer orderNum) {
        List<Customer> customers = customerRepository.findAllByOrderNumGreaterThanEqualAndDeletedFalseOrderByOrderNumAsc(orderNum);
        for (Customer customer : customers) {
            customer.setOrderNum(++orderNum);
        }
        customerRepository.save(customers);
    }
}
