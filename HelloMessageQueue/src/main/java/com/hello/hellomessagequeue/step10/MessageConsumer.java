package com.hello.hellomessagequeue.step10;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class MessageConsumer {

    private final StockRepository stockRepository;

    public MessageConsumer(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
    public void receiveMessage(StockEntity stockEntity, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
        try {
            System.out.println("[Consumer] " + stockEntity);
            Thread.sleep(200);
            Optional<StockEntity> optionalStockEntity = stockRepository.findById(stockEntity.getId());
            if (optionalStockEntity.isPresent()) {
                StockEntity stock = optionalStockEntity.get();
                stock.setUpdatedAt(LocalDateTime.now());
                StockEntity entity = stockRepository.save(stock);
                System.out.println("[Save Entity Consumer] " + entity);
            } else {
                throw new RuntimeException("Stock not found");
            }
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            System.out.println("[Consumer Error] " + e.getMessage());
            try {
                channel.basicNack(deliveryTag, false, false);
            } catch (Exception ex) {
                System.out.println("[Consumer send nack] " + ex.getMessage());
            }
        }
    }
}
