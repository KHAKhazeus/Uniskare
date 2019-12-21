package com.uniskare.eureka_skill.service.impl.RMQ;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;



/**
 * @author : SCH001
 * @description :
 */
@Component
public class PayTimeOutConsumer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = RMQConfig.Timeout_Trade_Queue_Name)
    public void process(String tradeCode, Message message, Channel channel) throws IOException {
        try {
            logger.info("开始执行订单[{}]的支付超时订单关闭......", tradeCode);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            logger.info("超时订单处理完毕");
        } catch (Exception e) {
            logger.error("超时订单处理失败:{}", e.getMessage());
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}