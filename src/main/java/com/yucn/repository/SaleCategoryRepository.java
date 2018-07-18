package com.yucn.repository;

import com.yucn.entity.SaleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */
public interface SaleCategoryRepository extends JpaRepository<SaleCategory, Long> {
    List<SaleCategory> findAllByDeletedFalse();
    SaleCategory findByIdAndDeletedFalse(Long id);
}
