package com.yucn.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/7.
 */
@Entity
@Data
public class CustomerFeeDetail {
    @Id
    @GeneratedValue
    private Long id;
    private Long customerId;
    private String name;
    private String nickName;
    private String mobile;
    private String address;
    private String saleStrategies;
    private BigDecimal accurAmmount;
    private BigDecimal balance;
    private Integer type;
    private String description;
    // 实际发生时间
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date occurTime;
}
