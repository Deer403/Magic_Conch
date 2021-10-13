package com.two.conchserver.utils;

//自定义异常的异常消息
public enum ErrorEnums {

    CODE_TYPE_ERROR("parameter error","The programming language type is entered incorrectly"),
    DELETE_FAILURE_ERROR("file","Delete file failure");

    private String title;
    private String message;


    private ErrorEnums(String title, String message){
        this.title = title;
        this.message = message;
    }

    @Override
    public String toString() {
        return "[" + title + "]: "+ message;
    }
}
