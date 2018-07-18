package com.yucn.service.impl;

import com.yucn.entity.SaleCategory;
import com.yucn.repository.CustomerRepository;
import com.yucn.repository.SaleCategoryRepository;
import com.yucn.repository.SaleStrategyRepository;
import com.yucn.service.SaleCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */
@Service
@Slf4j
public class SaleCategoryServiceImpl implements SaleCategoryService {
    @Autowired
    private SaleCategoryRepository saleCategoryRepository;
    @Autowired
    private SaleStrategyRepository saleStrategyRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<SaleCategory> list() {

        List<SaleCategory> saleCategoryList = saleCategoryRepository.findAllByDeletedFalse();
        return saleCategoryList;
    }

    @Override
    public SaleCategory add(SaleCategory saleCategory) {
        // 保存
        saleCategoryRepository.save(saleCategory);
        return saleCategory;
    }

    @Override
    public SaleCategory delete(Long id) {
        SaleCategory saleCategory = saleCategoryRepository.findOne(id);
        //saleCategory.setDeleted(true);
        //saleCategoryRepository.save(saleCategory);
        //
        //// 为商品种类下的所有销售方案做删除标记
        ////List<SaleStrategy> saleStrategies = saleCategory.getSaleStrategies();
        //saleStrategies.forEach(saleStrategy -> {
        //    saleStrategy.setDeleted(true);
        //});
        //saleStrategyRepository.save(saleStrategies);
        //// 为客户的销售方案做删除标记
        //List<Customer> customers = customerRepository.findAllByDeletedFalse();
        //customers.forEach(customer -> {
        //    List<SaleStrategy> saleStrategyList = customer.getSaleStrategies();
        //    saleStrategyList.removeAll(saleStrategies);
        //    customer.setSaleStrategies(saleStrategyList);
        //});
        //customerRepository.save(customers);
        return saleCategory;
    }
}
