package com.yucn.repository;

import com.yucn.entity.SaleStrategy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */
public interface SaleStrategyRepository extends JpaRepository<SaleStrategy, Long> {
    List<SaleStrategy> findAllByIdIsIn(List<String> ids);
}
