package com.yucn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/2/5.
 */
//@DynamicUpdate
@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    // 手动排序号
    private Integer orderNum;
    // 客户姓名
    private String name;
    // 客户昵称
    private String nickName;
    // 手机号
    private String mobile;
    // 住址
    private String address;
    // 充值额度
    private BigDecimal recharge;
    // 余额
    private BigDecimal balance;
    // 销售方案
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true,mappedBy = "customer")
    @JsonIgnoreProperties("customer")
    private List<SaleStrategy> saleStrategies=new ArrayList<>();
    @JsonIgnore
    private boolean deleted;
    // 过期日
    @Transient
    private Integer expireDays;
    // 排之前
    @Transient
    private Long suffixId;
    @JsonIgnore
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;
    @JsonIgnore
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateTime;
}
