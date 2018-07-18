package com.yucn.repository;

import com.yucn.entity.CustomerFeeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Created by Administrator on 2018/2/5.
 */
public interface CustomerFeeDetailRepository extends JpaRepository<CustomerFeeDetail, Long> {
    Page<CustomerFeeDetail> findByCustomerIdInOrderByIdDesc(Collection<Long> ids, Pageable pageable);
    Page<CustomerFeeDetail> findByCustomerIdInAndTypeEqualsOrderByIdDesc(Collection<Long> ids, Integer type,Pageable pageable);

    Page<CustomerFeeDetail> findByNameContainsOrderByIdDesc(String key, Pageable pageable);
    Page<CustomerFeeDetail> findByNameContainsAndTypeEqualsOrderByIdDesc(String key,Integer type, Pageable pageable);

    Page<CustomerFeeDetail> findByMobileContainsOrderByIdDesc(String key, Pageable pageable);
    Page<CustomerFeeDetail> findByMobileContainsAndTypeEqualsOrderByIdDesc(String key,Integer type, Pageable pageable);

    Page<CustomerFeeDetail> findByAddressContainsOrderByIdDesc(String key, Pageable pageable);
    Page<CustomerFeeDetail> findByAddressContainsAndTypeEqualsOrderByIdDesc(String key,Integer type, Pageable pageable);
}
