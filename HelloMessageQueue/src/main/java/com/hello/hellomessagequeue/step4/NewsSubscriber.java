package com.hello.hellomessagequeue.step4;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NewsSubscriber {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public NewsSubscriber(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.JAVA_QUEUE)
    public void javaNews(String message) {
        simpMessagingTemplate.convertAndSend("/topic/java", message);
    }

    @RabbitListener(queues = RabbitMQConfig.SPRING_QUEUE)
    public void springNews(String message) {
        simpMessagingTemplate.convertAndSend("/topic/spring", message);
    }

    @RabbitListener(queues = RabbitMQConfig.VUE_QUEUE)
    public void vueNews(String message) {
        simpMessagingTemplate.convertAndSend("/topic/vue", message);
    }
}
