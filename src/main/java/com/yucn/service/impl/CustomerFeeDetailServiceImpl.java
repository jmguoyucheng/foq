package com.yucn.service.impl;

import com.yucn.converter.Customer2CustomerFeeDetail;
import com.yucn.entity.Customer;
import com.yucn.enums.DetailTypeEnum;
import com.yucn.entity.CustomerFeeDetail;
import com.yucn.repository.CustomerFeeDetailRepository;
import com.yucn.repository.CustomerRepository;
import com.yucn.service.CustomerFeeDetailService;
import com.yucn.utils.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/5.
 */
@Service
@Slf4j
public class CustomerFeeDetailServiceImpl implements CustomerFeeDetailService {
    @Autowired
    private CustomerFeeDetailRepository customerFeeDetailRepository;

    @Override
    public Page<CustomerFeeDetail> findList(Integer type, String mode, String key, Pageable pageable) {
        Page<CustomerFeeDetail> customerFeeDetailPage = null;
        if(type== DetailTypeEnum.CUSTOMERFEE_DETAIL.getCode()) {
            if (mode.equals("byId")) {
                customerFeeDetailPage = customerFeeDetailRepository.findByCustomerIdInOrderByIdDesc(ConvertUtil.string2Longs(key),pageable);
            } else if (mode.equals("byName")) {
                customerFeeDetailPage = customerFeeDetailRepository.findByNameContainsOrderByIdDesc(key, pageable);
            } else if (mode.equals("byMobile")) {
                customerFeeDetailPage = customerFeeDetailRepository.findByMobileContainsOrderByIdDesc(key, pageable);
            } else {
                customerFeeDetailPage = customerFeeDetailRepository.findByAddressContainsOrderByIdDesc(key, pageable);
            }
        } else {
            if (mode.equals("byId")) {
                customerFeeDetailPage = customerFeeDetailRepository.findByCustomerIdInAndTypeEqualsOrderByIdDesc(ConvertUtil.string2Longs(key),type,pageable);
            } else if (mode.equals("byName")) {
                customerFeeDetailPage = customerFeeDetailRepository.findByNameContainsAndTypeEqualsOrderByIdDesc(key,type, pageable);
            } else if (mode.equals("byMobile")) {
                customerFeeDetailPage = customerFeeDetailRepository.findByMobileContainsAndTypeEqualsOrderByIdDesc(key,type, pageable);
            } else {
                customerFeeDetailPage = customerFeeDetailRepository.findByAddressContainsAndTypeEqualsOrderByIdDesc(key,type, pageable);
            }
        }
        return customerFeeDetailPage;
    }
}
