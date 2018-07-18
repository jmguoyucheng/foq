package com.yucn.service.impl;

import com.yucn.service.SaleCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/3/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SaleCategoryServiceImplTest {
    @Autowired
    private SaleCategoryService saleCategoryService;
    @Test
    public void list() throws Exception {
        saleCategoryService.list();
    }

}