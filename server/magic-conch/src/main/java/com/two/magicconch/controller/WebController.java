package com.two.magicconch.controller;

import com.two.magicconch.model.CodeModel;
import com.two.magicconch.service.RunCodeService;
import com.two.magicconch.utils.ProcessResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class WebController {

    public final RunCodeService runCodeService;

    public WebController(RunCodeService runCodeService) {
        this.runCodeService = runCodeService;
    }

    @PostMapping("/run")
    public ProcessResult runCode(@RequestBody CodeModel codeModel)throws Exception{
        return runCodeService.runCode(codeModel.getType(), codeModel.getCode());
    }
}
