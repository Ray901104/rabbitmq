package com.hello.hellomessagequeue.step3;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationSubscriber {

    public static final String CLIENT_URL = "/topic/notifications";
    // WebSocket 으로 메시지를 전달하기 위한 SPring 의 템플릿 클래스
    private final SimpMessagingTemplate simpMessagingTemplate;

    public NotificationSubscriber(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void subscribe(String message) {
        // RabbitMQ Queue 에서 메시지 수신
        System.out.println("Received Notification: " + message);
        // convertAndSend 를 통해 특정 경로로 메시지를 전달함
        simpMessagingTemplate.convertAndSend(CLIENT_URL, message);
    }
}
