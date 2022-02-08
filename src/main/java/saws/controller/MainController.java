package saws.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class MainController {

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
}
