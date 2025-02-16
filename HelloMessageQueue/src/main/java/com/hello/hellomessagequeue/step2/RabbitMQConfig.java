//package com.hello.hellomessagequeue.step2;
//
//import org.springframework.amqp.core.AcknowledgeMode;
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
//    public static final String QUEUE_NAME = "work-queue";
//
//    // 큐
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_NAME, true);
//    }
//
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
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        return container;
//    }
//
//    @Bean
//    public MessageListenerAdapter listenerAdapter(WorkQueueConsumer workQueueConsumer) {
//        return new MessageListenerAdapter(workQueueConsumer, "workQueueTask");
//    }
//}
