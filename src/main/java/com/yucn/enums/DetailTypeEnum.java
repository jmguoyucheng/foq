package com.yucn.enums;

import lombok.Getter;

/**
 * Created by Administrator on 2018/6/17.
 */
@Getter
public enum DetailTypeEnum implements CodeEnum {
    CUSTOMERFEE_DETAIL(100,"费用明细"),
    CUSTOMER_RECHARGE(101,"充值"),
    DEDUCT_EXPENSES(102,"送奶扣费"),
    COST_ADJUSTMENT(103,"费用调整"),
    ;
    private Integer code;
    private String message;

    DetailTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
