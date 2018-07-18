package com.yucn.service;

import com.yucn.entity.Customer;
import com.yucn.entity.CustomerFeeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * Created by Administrator on 2018/2/5.
 */
public interface CustomerFeeDetailService {
    Page<CustomerFeeDetail> findList(Integer type, String mode, String key, Pageable pageable);
}
