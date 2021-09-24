package com.two.conchserver.utils;

import lombok.Data;

@Data
public class ProcessResult {
    private int exitCode;
    private String output;

    public ProcessResult(int exitCode,String output){
        this.exitCode=exitCode;
        this.output = output;
    }

}
