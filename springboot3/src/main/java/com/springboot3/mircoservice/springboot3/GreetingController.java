package com.springboot3.mircoservice.springboot3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
public class GreetingController {

    @Value("${my.greeting}")
    private String greetingMessage;

    @Value("${gota: no gota right now}")//Assigning Default value if no property if present
    private String assigndefaultValueIfNoValueInAppProperty;

    @Value("${my.list.values}")
    private List<String> listValues;

    @Autowired
    private Environment env;//Bad idea to use it never use it

//    @Value("#{${dbValues}}")
//    private Map<String, String> dbValues;

    @Autowired
    private DbSetting dbSetting;

    @GetMapping("/dbsetting")
    public String dbsettingMethod(){
        return dbSetting.getConnection() + dbSetting.getHost() + dbSetting.getPort();
    }

//    @GetMapping("/dbvals")
//    public Map<String, String> mapOfdbValue(){
//        return dbValues;
//    }


    @GetMapping("/listOfString")
    public List<String> listOfValuesFromAppProperty(){
        return listValues;
    }

    @GetMapping("/greeting")
    public String greetings(){
        return greetingMessage;
    }

    @GetMapping("/gota")
    public String gotaAnnalyzer(){
        return assigndefaultValueIfNoValueInAppProperty;
    }

    @GetMapping("/envdetails")
    public String envDetails(){
        return env.toString();
    }
}
