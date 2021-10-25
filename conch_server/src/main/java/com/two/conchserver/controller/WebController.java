package com.two.conchserver.controller;

import com.two.conchserver.model.CodeVo;
import com.two.conchserver.service.RunCodeService;
import com.two.conchserver.utils.ErrorEnums;
import com.two.conchserver.utils.LanguageDetails;
import com.two.conchserver.utils.ProcessResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class WebController {

    public final RunCodeService runCodeService;

    public WebController(RunCodeService runCodeService) {
        this.runCodeService = runCodeService;
    }

    @PostMapping(value = "/run")
    public ProcessResult runCode(@RequestBody CodeVo codeVo){
        try {
            LanguageDetails type = LanguageDetails.valueOf(codeVo.getType());
            return runCodeService.runCodeDocker(type, codeVo.getCode());
        }catch (IllegalArgumentException e){
            return new ProcessResult(ErrorEnums.CODE_TYPE_ERROR.getErrorCode(), ErrorEnums.CODE_TYPE_ERROR.toString());
        }

    }

    @RequestMapping("/codetype")
    public List<LanguageDetails> codeType(){
        return List.of(LanguageDetails.values());
    }
}

