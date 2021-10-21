package com.two.conchserver.utils;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BusinessException(ErrorEnums errorEnums) {
        super(errorEnums.toString());
    }

}
