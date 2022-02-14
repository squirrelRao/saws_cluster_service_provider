package saws.massage;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public String exchange = "hiDirectExchange";
    public String queue = "hi";

    @Bean
    public Queue HiQueue(){
        return new Queue(queue);
    }

    @Bean
    public DirectExchange RabbitConfig() {
        return new DirectExchange(this.exchange);
    }
}
