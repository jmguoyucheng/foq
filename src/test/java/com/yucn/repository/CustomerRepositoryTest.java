package com.yucn.repository;

import com.yucn.entity.Customer;
import com.yucn.entity.SaleCategory;
import com.yucn.entity.SaleStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/2/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void saveTest() {
        Customer customer = new Customer();
        customer.setName("郭玉成");
        customer.setAddress("润发家园");
        customer.setMobile("13792897868");
        customer.setBalance(new BigDecimal(100.5));

        SaleCategory saleCategory = new SaleCategory();
        saleCategory.setName("鲜奶");

        SaleStrategy saleStrategy1 = new SaleStrategy();
        saleStrategy1.setAmount(1);
        saleStrategy1.setTotalPrice(new BigDecimal(4));

        SaleStrategy saleStrategy2 = new SaleStrategy();
        saleStrategy2.setAmount(2);
        saleStrategy2.setTotalPrice(new BigDecimal(7.6));


        //SaleCategory到Customer的关系
        saleStrategy1.setCustomer(customer);
        saleStrategy2.setCustomer(customer);

        //Customer到SaleStrategy的关系
        customer.getSaleStrategies().add(saleStrategy1);
        customer.getSaleStrategies().add(saleStrategy2);

        //SaleStrategy到SaleCategory的关系
        saleStrategy1.setSaleCategory(saleCategory);
        saleStrategy2.setSaleCategory(saleCategory);

        //saleCategory到SaleStrategy的关系
        saleCategory.getSaleStrategies().add(saleStrategy1);
        saleCategory.getSaleStrategies().add(saleStrategy2);

        customerRepository.save(customer);
    }

    //@Test
    //@Transactional
    //public void findOne() {
    //    Customer customer = customerRepository.findOne("201806141401");
    //    List<SaleStrategy> saleStrategies=customer.getSaleStrategies();
    //    System.out.println(saleStrategies.size());
    //}
    //
    //@Test
    //public void findAllByStartDate() {
    //    List<Customer> customers = customerRepository.findAllByStartDateBeforeAndDeletedFalse(new Date());
    //    System.out.println(new Date());
    //    System.out.println(customers);
    //}
    //
    //@Test
    //public void findAllByOrderIdGreaterThanEqual() {
    //    List<Customer> customers = customerRepository.findAllByOrderNumGreaterThanEqualAndDeletedFalseOrderByOrderNumAsc(10);
    //    for (Customer customer : customers) {
    //        System.out.println(customer.getOrderNum());
    //    }
    //}
    //
    //@Test
    //public void findFirstByOrderNumGreaterThan() {
    //    Customer customer = customerRepository.findFirst1ByOrderNumGreaterThanOrderByOrderNumAsc(10);
    //    log.info("姓名：{},排序号：{}", customer.getName(), customer.getOrderNum());
    //}
    //
    //@Test
    //public void findFirstOrderByOrderNum() {
    //    Customer customer = customerRepository.findTopByOrderByOrderNumDesc();
    //    log.info("姓名：{},排序号：{}", customer.getName(), customer.getOrderNum());
    //}

    @Test
    public void findByIdInTest() {
        //定义字符串
        String str = "1.2,3,4,5,6,7,8,9,10";
//截取字符串
        String[] strArr = str.split(",");
//转换long类型的数组
        Set<Long> ids = new HashSet<>();
        for (int i = 0; i < strArr.length; i++) {
            try {
                ids.add(Long.valueOf(strArr[i]));
            } catch (Exception e) {

            }
        }

        //List<Customer> customers =customerRepository.findByIdInAndDeletedFalse(ids);
        System.out.println(ids.size());
    }
}