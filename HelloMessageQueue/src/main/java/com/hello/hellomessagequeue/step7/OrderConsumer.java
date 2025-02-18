//package com.hello.hellomessagequeue.step7;
//
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class OrderConsumer {
//
//    private static final int MAX_RETRIES = 3; // 총 시도 제한 수
//    private int retryCount = 0; // 재시도 횟수
//
//    @RabbitListener(queues = RabbitMQConfig.ORDER_COMPLETED_QUEUE, containerFactory = "rabbitListenerContainerFactory")
//    public void processOrder(String message, Channel channel, @Header("amqp_deliveryTag") long tag) {
//        try {
//            // 실패 유발
//            if ("fail".equalsIgnoreCase(message)) {
//                if (retryCount < MAX_RETRIES) {
//                    System.err.println("[Consumer] 실패, 재시도 = " + retryCount);
//                    retryCount++;
//                    throw new RuntimeException(message);
//                } else {
//                    System.err.println("[Consumer] 최대 재시도 횟수 초과, DLQ 이동");
//                    retryCount = 0;
//                    channel.basicNack(tag, false, false);
//                    return;
//                }
//            }
//            System.out.println("[Consumer] 성공 : " + message);
//            channel.basicAck(tag, false);
//            retryCount = 0;
//        } catch (Exception e) {
//            System.err.println("[Consumer] 예외 발생 : " + e.getMessage());
//            try {
//                // 실패 시 basicReject 처리하여 메시지를 다시 처리
//                channel.basicReject(tag, true);
//            } catch (IOException ex) {
//                System.err.println("[Consumer] fail & reject message : " + ex.getMessage());
//            }
//        }
//    }
//}
