package com.yucn.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2018/2/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SaleStrategyRepositoryTest {
    @Autowired
    private SaleStrategyRepository saleStrategyRepository;
    @Autowired
    private SaleCategoryRepository saleCategoryRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void saveTest(){
        //SaleStrategy saleStrategy = new SaleStrategy();
        //saleStrategy.setAmount(2);
        //SaleCategory saleCategory = saleCategoryRepository.findByIdAndDeletedFalse("8a8b23b363fc5eee0163fc5ef7b50000");
        //Customer customer=customerRepository.findByIdAndDeletedFalse("201806141401");
        //saleStrategy.setSaleCategory(saleCategory);
        //saleStrategy.setCustomer(customer);
        //saleStrategyRepository.save(saleStrategy);
        //Assert.assertNotNull(saleStrategy.getId());
    }

    @Test
    public void list(){
        //SaleStrategy saleStrategy = saleStrategyRepository.findOne("8a8b23b3616d581c01616d58240b0000");
        //log.info(saleStrategy.toString());
    }

}