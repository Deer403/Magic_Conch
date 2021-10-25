package com.two.conchserver.utils;

//自定义异常的异常消息
public enum ErrorEnums {

    CODE_TYPE_ERROR(400,"parameter error","The programming language type is entered incorrectly"),
    DELETE_FAILURE_ERROR(401,"file","Delete file failure"),
    TIME_OUT(402,"time out","programming wait time is long");

    private String title;
    private String message;
    private int errorCode;


    private ErrorEnums(int errorCode,String title, String message){
        this.errorCode = errorCode;
        this.title = title;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "[" + title + "]: "+ message;
    }
}
