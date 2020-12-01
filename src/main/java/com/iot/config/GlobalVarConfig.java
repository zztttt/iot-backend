package com.iot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalVarConfig {
    @Value("${getDataScriptPath}")
    public String getDataScriptPath;

    @Value("${openMotorScriptPath}")
    public String openMotorScriptPath;

    @Value("${closeMotorScriptPath}")
    public String closeMotorScriptPath;
}
