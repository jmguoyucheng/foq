package com.yucn.repository;

import com.yucn.entity.SaleCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SaleCategoryRepositoryTest {
    @Autowired
    private SaleCategoryRepository saleCategoryRepository;

    @Test
    public void saveTest(){
        SaleCategory saleCategory=new SaleCategory();
        saleCategory.setName("鲜奶");
        saleCategoryRepository.save(saleCategory);
    }

    @Test
    public void findAll(){
        List<SaleCategory> saleCategories = saleCategoryRepository.findAll();
        //log.info(saleCategories.toString());
    }

    @Test
    public void findByIdTest(){
        //SaleCategory saleCategory=saleCategoryRepository.findByIdAndDeletedFalse("8a8b23b363fc5eee0163fc5ef7b50000");
        //System.out.println(ReflectionToStringBuilder.toString(saleCategory, ToStringStyle.MULTI_LINE_STYLE));
    }
}