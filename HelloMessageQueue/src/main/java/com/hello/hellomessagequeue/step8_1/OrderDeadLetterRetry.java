//package com.hello.hellomessagequeue.step8_1;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OrderDeadLetterRetry {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    public OrderDeadLetterRetry(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.DLQ)
//    public void process(String message) {
//        System.out.println("[OrderDeadLetterRetry] DLQ 메시지 처리 : " + message);
//
//        try {
//            if ("fail".equalsIgnoreCase(message)) {
//                message = "success";
//                System.out.println("[OrderDeadLetterRetry] DLQ 메시지 처리 완료 : " + message);
//            } else {
//                System.err.println("[OrderDeadLetterRetry] Message already fixed. Ignoring : " + message);
//                return;
//            }
//            rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_COMPLETED_EXCHANGE, "order.completed", message);
//            System.out.println("[OrderDeadLetterRetry] requeued message : " + message);
//        } catch (Exception e) {
//            System.err.println("[OrderDeadLetterRetry] Failed to reprocess message : " + e.getMessage());
//        }
//    }
//}
