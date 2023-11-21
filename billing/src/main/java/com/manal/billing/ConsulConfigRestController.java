package com.manal.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class ConsulConfigRestController {

    @Autowired
    private MyConsulConfig myConsulConfig;
    @Autowired
    private MyVaultConfig myVaultConfig;
   // @Value("${token.accessTokenTimeout}")
   // private long accessTokenTimeout;
   // @Value("${Token.refreshTokenTimeout}")
    // private long refreshTokenTimeout;

    /* @GetMapping("/myConfig")
    public  MyConsulConfig myConfig(){
         return myConsulConfig;


    }*/
    @GetMapping("/myConfig")
    public Map<String,Object> myConfig()
    {
        return Map.of("consultConfig",myConsulConfig,"vaultConfig",myVaultConfig);
    }

}
