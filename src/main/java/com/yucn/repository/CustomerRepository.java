package com.yucn.repository;

import com.yucn.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findByNameContainsAndDeletedFalse(String key, Pageable pageable);
    Page<Customer> findByNameContainsAndDeletedTrue(String key, Pageable pageable);

    Page<Customer> findByMobileContainsAndDeletedFalse(String key, Pageable pageable);
    Page<Customer> findByMobileContainsAndDeletedTrue(String key, Pageable pageable);

    Page<Customer> findByAddressContainsAndDeletedFalse(String key, Pageable pageable);
    Page<Customer> findByAddressContainsAndDeletedTrue(String key, Pageable pageable);

    Customer findByIdAndDeletedFalse(Long id);

    Page<Customer> findByIdInAndDeletedFalse(Collection<Long> ids, Pageable pageable);

    // 查询所有没有删除标记的客户列表
    List<Customer> findAllByDeletedFalse();

    //查询所有排序号大于等于  orderId的记录
    List<Customer> findAllByOrderNumGreaterThanEqualAndDeletedFalseOrderByOrderNumAsc(Integer orderNum);

    Customer findFirst1ByOrderNumGreaterThanOrderByOrderNumAsc(Integer orderNum);

    Customer findFirstByOrderByOrderNumDesc();

    Customer findTopByOrderByOrderNumDesc();
}
