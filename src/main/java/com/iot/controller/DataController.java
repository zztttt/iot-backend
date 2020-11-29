package com.iot.controller;

import com.iot.entity.Temperature;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicLong;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DataController {
    private final AtomicLong temperatureCounter = new AtomicLong();

    @GetMapping("/getTemperature")
    public Temperature getTemperature() {
        long cur  = System.currentTimeMillis();
        try {
            long ret = callScript("D:\\zztttt\\研一\\物联网大作业\\code\\iot\\src\\main\\resources\\python\\query.py");
            //log.info("success");
            return new Temperature(cur, ret);
        }catch (Exception e){
            e.printStackTrace();
            return new Temperature(cur, temperatureCounter.incrementAndGet());
        }
    }

    private long callScript(String scriptPath) throws InterruptedException, IOException {
        ProcessBuilder pb = new ProcessBuilder().command("python", "-u", scriptPath);
        Process p = pb.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line = bufferedReader.readLine()) != null){
            sb.append(line);
        }
        long result = p.waitFor();
        log.info("Value is: " + sb.toString());
        log.info("Process exit value:" + result);
        bufferedReader.close();

        return Long.valueOf(result);
    }
}
