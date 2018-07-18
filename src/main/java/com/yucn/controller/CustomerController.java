package com.yucn.controller;

import com.yucn.entity.Customer;
import com.yucn.enums.DetailTypeEnum;
import com.yucn.service.CustomerService;
import com.yucn.utils.ResultVOUtil;
import com.yucn.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */
@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResultVO<Customer> add(@RequestBody Customer customerDto) {
        // 添加客户
        Customer customer = customerService.add(customerDto);
        log.info("添加客户，客户ID为：{}", customer.getId());
        return ResultVOUtil.success(customer);
    }

    @GetMapping(value = "/list")
    public ResultVO<List<Customer>> list(@RequestParam(value = "listMode", defaultValue = "byAddress") String mode,
                                         @RequestParam(value = "keyValue", defaultValue = "") String key,
                                         @RequestParam(value = "currentPage", defaultValue = "1") Integer page,
                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer size) {
        log.info("查询客户列表");
        Sort sort = new Sort(Sort.Direction.ASC, "orderNum");
        PageRequest request = new PageRequest(page - 1, size, sort);
        Page<Customer> customerPage = customerService.findList(mode, key, request);
        System.out.println(customerPage);
        return ResultVOUtil.success(customerPage);
    }

    @PostMapping(value = "/recharge")
    public ResultVO<Customer> recharge(@RequestParam(value = "id") Long id,
                                       @RequestParam(value = "cost", defaultValue = "0") BigDecimal cost) {
        log.info("为客户[{}]充值{}元", id, cost);
        Customer customer = customerService.adjustCost(id, cost, DetailTypeEnum.CUSTOMER_RECHARGE, "");
        return ResultVOUtil.success(customer);
    }

    @PostMapping(value = "/adjustcost")
    public ResultVO<Customer> adjustCost(@RequestParam(value = "id") Long id,
                                         @RequestParam(value = "cost", defaultValue = "0") BigDecimal cost,
                                         String description) {
        log.info("为客户[{}]调整费用{}元", id, cost);
        System.out.println(description);
        Customer customer = customerService.adjustCost(id, cost, DetailTypeEnum.COST_ADJUSTMENT, description);
        return ResultVOUtil.success(customer);
    }

    @GetMapping(value = "/content")
    public ResultVO<Customer> content(@RequestParam Long id) {
        log.info("查询客户【{}】详情", id);
        Customer customer = customerService.findOne(id);
        return ResultVOUtil.success(customer);
    }

    @PostMapping(value = "/modify")
    public ResultVO<Customer> modify(@RequestBody Customer customer) {
        log.info("修改客户【{}】", customer.getId());
        return ResultVOUtil.success(customerService.modify(customer));
    }

    @PostMapping(value = "/delete")
    public ResultVO<Customer> delete(@RequestParam Long id) {
        log.info("删除客户【{}】", id);
        return ResultVOUtil.success(customerService.delete(id));
    }

    @GetMapping("/deleted")
    public ResultVO<List<Customer>> listDeleted(@RequestParam(value = "listMode", defaultValue = "byName") String mode,
                                                @RequestParam(value = "keyValue", defaultValue = "") String key,
                                                @RequestParam(value = "currentPage", defaultValue = "1") Integer page,
                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer size) {
        log.info("查询已删除客户列表");
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest request = new PageRequest(page - 1, size, sort);
        Page<Customer> customerPage = customerService.findListDeleted(mode, key, request);
        return ResultVOUtil.success(customerPage);
    }
}
