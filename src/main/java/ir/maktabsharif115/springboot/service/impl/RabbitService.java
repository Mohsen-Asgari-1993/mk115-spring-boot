package ir.maktabsharif115.springboot.service.impl;

import com.rabbitmq.client.Channel;
import ir.maktabsharif115.springboot.config.RabbitMQConfiguration;
import ir.maktabsharif115.springboot.service.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitService {

    private final RabbitTemplate rabbitTemplate;

    public void publishMessage() {
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(
                    RabbitMQConfiguration.TOPIC,
                    RabbitMQConfiguration.CUSTOMER_QUEUE,
                    new CategoryDTO((long) i, i + "", null)
            );
        }
    }

    @RabbitListener(queues = RabbitMQConfiguration.CUSTOMER_QUEUE)
    public void listenToRabbit(CategoryDTO message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        log.info("message received id: {}, title: {}", message.getId(), message.getTitle());
    }
}
