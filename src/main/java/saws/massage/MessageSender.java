package saws.massage;

import io.micrometer.core.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class MessageSender {

    @Autowired
    AmqpTemplate rabbitTemplate;

    @Autowired
    RabbitConfig rabbitConfig;

    public void sendDirectMessage(String msg) {
        String messageId = String.valueOf(Math.random());
        String messageData = msg;
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        if (rabbitTemplate == null) {
            log.info("test");
            return;
        }
        rabbitTemplate.convertAndSend(rabbitConfig.queue, map);
        log.info("发送消息成功: "+map);
    }
}


