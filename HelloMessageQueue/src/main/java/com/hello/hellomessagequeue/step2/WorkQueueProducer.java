package com.hello.hellomessagequeue.step2;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class WorkQueueProducer {

    private final RabbitTemplate rabbitTemplate;

    public WorkQueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendWorkQueue(String message, int duration) {
        String workMessage = message + "| " + duration;
        rabbitTemplate.convertAndSend(RabbitMQConfigStep2.QUEUE_NAME, workMessage);
        System.out.println("# Sent workqueue : " + workMessage);
    }
}
