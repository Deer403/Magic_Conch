package com.two.magicconch.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "run.script")
@Component
@Data
public class Config {

    private String python;


}
