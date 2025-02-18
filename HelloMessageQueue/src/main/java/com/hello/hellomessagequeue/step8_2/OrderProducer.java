package com.hello.hellomessagequeue.step8_2;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendShipping(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_COMPLETED_EXCHANGE, "order.completed", message);
        System.out.println("[Publisher] 주문 완료. 배송 지시 메시지 생성 : " + message);
    }
}
