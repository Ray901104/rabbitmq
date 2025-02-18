package com.hello.hellomessagequeue.step8_2;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private int retryCount = 0;

    @RabbitListener(queues = RabbitMQConfig.ORDER_COMPLETED_QUEUE)
    public void consume(String message) {
        System.out.println("[Consumer] Received : " + message + " # retry : " + retryCount++);

        if ("fail".equalsIgnoreCase(message)) {
            throw new RuntimeException(message);
        }
        System.out.println("[Consumer] Success : " + message);
    }
}
