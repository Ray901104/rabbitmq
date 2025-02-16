//package com.hello.hellomessagequeue.step1;
//
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    // 큐 네임 설정
//    public static final String QUEUE_NAME = "hello-queue";
//
//    // 큐
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_NAME, false);
//    }
//
//    // rabbitMQ의 연결을 관리하는 템플릿
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        return new RabbitTemplate(connectionFactory);
//    }
//
//    @Bean
//    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(QUEUE_NAME);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }
//
//    @Bean
//    public MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }
//}
