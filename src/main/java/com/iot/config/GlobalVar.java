package com.iot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalVar {
    @Value("${scriptPath}")
    public String scriptPath;
}