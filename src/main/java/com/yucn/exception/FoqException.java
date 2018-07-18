package com.yucn.exception;

import com.yucn.enums.ResultEnum;

/**
 * Created by Administrator on 2018/2/5.
 */
public class FoqException extends RuntimeException {
    private Integer code;

    public FoqException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public FoqException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
