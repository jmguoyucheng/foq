package com.yucn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售策略
 * Created by Administrator on 2018/2/7.
 */
@Entity
@Data
public class SaleStrategy {
    @Id
    @GeneratedValue
    private Long id;
    // 数量
    private Integer amount;
    // 价格
    private BigDecimal totalPrice;
    // 送奶周期cron表达式
    private String cron;
    // 生效时间
    private Date startTime;
    // 失效时间
    private Date endTime;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("saleStrategies")
    private SaleCategory saleCategory;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("saleStrategies")
    private Customer customer;
    @Transient
    private boolean enable;

    @Override
    public String toString() {
        return "SaleStrategy{" +
                "id=" + id +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                ", cron='" + cron + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", enable=" + enable +
                '}';
    }
}
