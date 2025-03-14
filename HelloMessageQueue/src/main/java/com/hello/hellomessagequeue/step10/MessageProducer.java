package com.hello.hellomessagequeue.step10;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class MessageProducer {

    private final StockRepository stockRepository;

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(StockRepository stockRepository, RabbitTemplate rabbitTemplate) {
        this.stockRepository = stockRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public void sendMessage(StockEntity stockEntity, boolean testCase) {
        stockEntity.setProcessed(false);
        stockEntity.setCreatedAt(LocalDateTime.now());
        StockEntity entity = stockRepository.save(stockEntity);

        System.out.println("[Producer entity] : " + entity);

        if (stockEntity.getUserId() == null || stockEntity.getUserId().isEmpty()) {
            throw new RuntimeException("User id is required");
        }

        try {
            // 메시지를 RabbitMQ 에 전송
            CorrelationData correlationData = new CorrelationData(entity.getId().toString());
            rabbitTemplate.convertAndSend(
                    testCase ? "nonExistentExchange" : RabbitMQConfig.EXCHANGE_NAME,
                    testCase ? "invalidRoutingKey" : RabbitMQConfig.ROUTING_KEY,
                    entity,
                    correlationData
            );

            if (correlationData.getFuture().get(5, TimeUnit.SECONDS).isAck()) {
                System.out.println("[Producer correlationData] 성공 " + entity);
                entity.setProcessed(true);
                stockRepository.save(entity);
            } else {
                throw new RuntimeException("# confirm 실패 - 롤백");
            }
        } catch (Exception e) {
            System.out.println("[Producer exception fail] : " + e);
            throw new RuntimeException(e);
        }
    }
}
