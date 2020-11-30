package com.iot.controller;

import com.iot.entity.Temperature;
import com.iot.config.GlobalVar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.*;

@Slf4j
@RestController
public class DataController {
    @Autowired
    GlobalVar globalVar;

    private final AtomicLong temperatureCounter = new AtomicLong();

    @GetMapping("/getData")
    public JSONObject getTemperature() {
        long cur  = System.currentTimeMillis();
        try {
            JSONObject ret = callScript(globalVar.scriptPath);
            //log.info("success");
            return ret;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject callScript(String scriptPath) throws InterruptedException, IOException {
        ProcessBuilder pb = new ProcessBuilder().command("python", "-u", scriptPath);
        Process p = pb.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        JSONObject ret = new JSONObject();
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line = bufferedReader.readLine()) != null){
            sb.append(line);
            if(line.length() >= 6) {
                String tmp = line.substring(0, 6);
                if(tmp.equals("result")){
                    String json = line.substring(9, line.length() - 1);
                    JSONObject jsonObject = JSON.parseObject(json);
                    //System.out.println(jsonObject.get("deviceStatusList"));
                    JSONArray deviceStatusList = (JSONArray)jsonObject.get("deviceStatusList");
                    deviceStatusList.forEach(item->{
                        JSONObject itemJson = (JSONObject)item;
                        String datasetId = (String) itemJson.get("datasetId");
                        if(datasetId.equals("temperature_data")){
                            log.info("temperature");
                            ret.put("temperature", itemJson.get("value"));
                        }else if(datasetId.equals("humidity_data")){
                            log.info("humidity");
                            ret.put("humidity", itemJson.get("value"));
                        }
                    });
                }
            }
        }
        long result = p.waitFor();
        log.info("Value is: " + sb.toString());
        log.info("Process exit value:" + result);
        bufferedReader.close();

        return ret;
    }
}
