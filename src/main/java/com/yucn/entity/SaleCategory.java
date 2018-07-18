package com.yucn.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/8.
 */
@Entity
@Data
public class SaleCategory {
    @Id
    @GeneratedValue
    private Long id;
    //商品类别名称
    private String name;
    @OneToMany(mappedBy = "saleCategory")
    @JsonIgnoreProperties("saleCategory")
    @JsonIgnore
    private List<SaleStrategy> saleStrategies=new ArrayList<>();
    @JsonIgnore
    private boolean deleted;
}
