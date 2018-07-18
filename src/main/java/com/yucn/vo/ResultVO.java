package com.yucn.vo;

import lombok.Data;

/**
 * Created by Administrator on 2018/1/11.
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}
