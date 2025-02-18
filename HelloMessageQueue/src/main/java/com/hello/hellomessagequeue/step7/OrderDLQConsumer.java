package com.hello.hellomessagequeue.step7;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderDLQConsumer {

    private final RabbitTemplate rabbitTemplate;

    public OrderDLQConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.DLQ)
    public void process(String message) {
        System.out.println("[DLQ Consumer] DLQ 메시지 처리 : " + message);

        try {
            String fixMessage = "success";
            rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_COMPLETED_EXCHANGE, "order.completed.shipping", fixMessage);
            System.out.println("[DLQ Consumer] DLQ 메시지 처리 완료 : " + message);
        } catch (Exception e) {
            System.err.println("[DLQ Consumer] DLQ 메시지 처리 실패 : " + e.getMessage());
        }
    }
}
