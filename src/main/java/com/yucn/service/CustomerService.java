package com.yucn.service;

import com.yucn.entity.Customer;
import com.yucn.enums.DetailTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/2/5.
 */
public interface CustomerService {
    Customer add(Customer customer);

    Page<Customer> findList(String mode, String key, Pageable pageable);

    Customer findOne(Long id);

    Customer adjustCost(Long id, BigDecimal recharge, DetailTypeEnum detailType, String description);

    Customer modify(Customer customerDto);

    Customer delete(Long id);

    Page<Customer> findListDeleted(String mode, String key, Pageable pageable);
}
