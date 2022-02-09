package saws.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class MainController {

//    @Value("${name}")
    private String name;

//    @Value("${domain}")
    private String domain;

    @GetMapping(value = "/hello")
    @HystrixCommand(fallbackMethod = "errorFallback", commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "100")})
    public String hello() throws InterruptedException {

        Thread.sleep(200);
        return "hello eureka!";
    }

    public String errorFallback(){
        log.info("超时降级");
        return "请求/hello降级";
    }

    @GetMapping(value="/config")
    public Map<String,String> getConfig(){

        Map<String,String> res = new HashMap<String,String>();

        res.put("name",this.name);
        res.put("domain",this.domain);

        return res;

    }
}
