package com.yucn.service;

import com.yucn.entity.SaleStrategy;

/**
 * Created by Administrator on 2018/2/7.
 */
public interface SaleStrategyService {
    //Page<SaleStrategyDto> findList(String mode, String key, Pageable pageable);

    SaleStrategy add(SaleStrategy saleStrategyDto);

    SaleStrategy delete(Long id);
}
