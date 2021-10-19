package com.two.conchserver.controller;

import com.two.conchserver.model.CodeModel;
import com.two.conchserver.service.RunCodeService;
import com.two.conchserver.utils.LanguageDetails;
import com.two.conchserver.utils.ProcessResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;

@RestController
@CrossOrigin("*")
public class WebController {

    public final RunCodeService runCodeService;

    public WebController(RunCodeService runCodeService) {
        this.runCodeService = runCodeService;
    }

    @PostMapping(value = "/run")
    public ProcessResult runCode(@RequestBody CodeModel codeModel)throws Exception{
//        return runCodeService.runCode(codeModel.getType(), codeModel.getCode());
       return runCodeService.runCodeDocker(LanguageDetails.valueOf(codeModel.getType()),codeModel.getCode());
    }

    @RequestMapping("/codetype")
    public String[] codeType(){
        String typeList[] = {"PYTHON3","CPP","JAVA","GOLANG"};
        return typeList;
    }
}

