//package com.hello.hellomessagequeue.step8_1;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    public static final String ORDER_COMPLETED_QUEUE = "order_completed_queue";
//    public static final String ORDER_COMPLETED_EXCHANGE = "order_completed_exchange";
//    public static final String DLQ = "deadLetterQueue";
//    public static final String DLX = "deadLetterExchange";
//    public static final String DEAD_LETTER_ROUTING_KEY = "dead.letter";
//
//    @Bean
//    public TopicExchange orderExchange() {
//        return new TopicExchange(ORDER_COMPLETED_EXCHANGE);
//    }
//
//    @Bean
//    public TopicExchange deadLetterExchange() {
//        return new TopicExchange(DLX);
//    }
//
//    // 메시지가 처리되지 못했을 경우 자동으로 DeadLeeterQueue 이동시킴
//    @Bean
//    public Queue orderQueue() {
//        return QueueBuilder.durable(ORDER_COMPLETED_QUEUE)
//                .withArgument("x-dead-letter-exchange", DLX) // Dead Letter Exchange 설정
//                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY)
//                .build();
//    }
//
//    @Bean
//    public Queue deadLetterQueue() {
//        return new Queue(DLQ);
//    }
//
//    @Bean
//    public Binding orderCompletedBinding() {
//        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with("order.completed");
//    }
//
//    @Bean
//    public Binding deadLetterBinding() {
//        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DEAD_LETTER_ROUTING_KEY);
//    }
//}
