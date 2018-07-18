package com.yucn.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

/**
 * Created by Administrator on 2018/2/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {
    @Autowired
    private CustomerServiceImpl service;
    //@Test
    //public void add() throws Exception {
    //    CustomerDto customerDto = new CustomerDto();
    //    customerDto.setName("郭玉成");
    //    customerDto.setAddress("润发家园");
    //    customerDto.setMobile("13792897868");
    //    customerDto.setRecharge(new BigDecimal(100));
    //    customerDto.setBalance(new BigDecimal(100));

        //List<SaleStrategyDto> saleStrategyDtos = new ArrayList<>();
        //SaleStrategyDto saleStrategyDto1 = new SaleStrategyDto();
        //saleStrategyDto1.setId("8a8b23b363fccb3d0163fccb47260000");
        //saleStrategyDtos.add(saleStrategyDto1);
        //SaleStrategyDto saleStrategyDto2 = new SaleStrategyDto();
        //saleStrategyDto2.setId("8a8b23b36208cee4016208d00ecc0001");
        //saleStrategyDtos.add(saleStrategyDto2);
        //customerDto.setSaleStrategyDtos(saleStrategyDtos);
    //    Customer customer = service.add(customerDto);
    //    System.out.println(ReflectionToStringBuilder.toString(customer, ToStringStyle.MULTI_LINE_STYLE));
    //}

    @Test
    public void chargeTest() throws ParseException {
        service.charge();
    }

}