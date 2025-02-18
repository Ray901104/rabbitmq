package com.hello.hellomessagequeue.step8_1;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private final RabbitTemplate rabbitTemplate;
    private final RetryTemplate retryTemplate;

    public OrderConsumer(RabbitTemplate rabbitTemplate, RetryTemplate retryTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.retryTemplate = retryTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_COMPLETED_QUEUE)
    public void consume(String message) {
        retryTemplate.execute(context -> {
            try {
                System.out.println("[Consumer] Received : " + message + " # retry : " + context.getRetryCount());

                if ("fail".equalsIgnoreCase(message)) {
                    throw new RuntimeException(message);
                }
                System.out.println("[Consumer] Success : " + message);
            } catch (Exception e) {
                if (context.getRetryCount() >= 2) {
                    rabbitTemplate.convertAndSend(RabbitMQConfig.DLX, RabbitMQConfig.DEAD_LETTER_ROUTING_KEY, message);
                } else {
                    throw e;
                }
            }
            return null;
        });
    }
}
