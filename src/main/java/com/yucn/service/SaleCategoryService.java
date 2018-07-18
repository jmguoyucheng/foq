package com.yucn.service;

import com.yucn.entity.SaleCategory;

import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */
public interface SaleCategoryService {
    List<SaleCategory> list();

    SaleCategory add(SaleCategory saleCategory);

    SaleCategory delete(Long id);
}
