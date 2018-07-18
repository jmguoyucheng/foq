package com.yucn.controller;

import com.yucn.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by Administrator on 2018/6/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private CustomerService customerService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //@Test
    //public void add() throws Exception {
    //    CustomerDto customerDto = new CustomerDto();
    //    customerDto.setName("郭玉成");
    //    customerDto.setAddress("润发家园");
    //    customerDto.setMobile("13792897868");
    //    customerDto.setRecharge(new BigDecimal(100));
    //    customerDto.setBalance(new BigDecimal(100));
    //
    //    customerService.add(customerDto);
    //}

    @Test
    public void modifyStrategyTest() throws Exception{
        String result = mockMvc.perform(post("/customer/modifystrategy/2")
                //.param("age", "18")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                //.andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

}