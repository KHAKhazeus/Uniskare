package com.uniskare.eureka_skill.service.impl.RMQ;

/**
 * @author : SCH001
 * @description :
 */

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RMQConfig {

    // 支付超时延时交换机
    public static final String Delay_Exchange_Name = "delay.exchange";

    // 超时订单关闭队列
    public static final String Timeout_Trade_Queue_Name = "close_trade";


    @Bean
    public Queue delayPayQueue() {
        return new Queue(RMQConfig.Timeout_Trade_Queue_Name, true);
    }


    // 定义广播模式的延时交换机 无需绑定路由
    @Bean
    FanoutExchange delayExchange(){
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        FanoutExchange topicExchange = new FanoutExchange(RMQConfig.Delay_Exchange_Name, true, false, args);
        topicExchange.setDelayed(true);
        return topicExchange;
    }

    // 绑定延时队列与交换机
    @Bean
    public Binding delayPayBind() {
        return BindingBuilder.bind(delayPayQueue()).to(delayExchange());
    }

    // 定义消息转换器
    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 定义消息模板用于发布消息，并且设置其消息转换器
    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
    @Bean
    RabbitAdmin rabbitAdmin(final ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

}
