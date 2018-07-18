package com.yucn.converter;

import com.yucn.entity.Customer;
import com.yucn.entity.CustomerFeeDetail;
import com.yucn.entity.SaleStrategy;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/3/6.
 */
public class Customer2CustomerFeeDetail {
    public static CustomerFeeDetail convert(Customer customer,String description) {
        CustomerFeeDetail customerFeeDetail = new CustomerFeeDetail();
        BeanUtils.copyProperties(customer, customerFeeDetail,"id");
        customerFeeDetail.setCustomerId(customer.getId());
        customerFeeDetail.setDescription(description);
        return customerFeeDetail;
    }

    public static List<CustomerFeeDetail> convert(List<Customer> customerList,String description) {
        return customerList.stream().map(e -> convert(e,description)).collect(Collectors.toList());
    }
}
