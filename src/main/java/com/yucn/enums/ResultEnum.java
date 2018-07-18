package com.yucn.enums;

import lombok.Getter;

/**
 * Created by Administrator on 2018/1/12.
 */
@Getter
public enum ResultEnum {
    CUSTOMER_NOT_EXIST(10, "客户不存在"),

    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
