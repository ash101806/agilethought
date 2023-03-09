package com.ashleyzapien.kafkapractice.app;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class MessageConsumer {
	private static final Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);
	@KafkaListener(topics = "${app.topic.name}", groupId = "${app.topic.groupid}")
	public void escuchar(@Payload ConsumerRecord<String, String> mensaje,@Header(required = false,value = KafkaHeaders.RECEIVED_PARTITION) int partition, @Header(required = false,value = KafkaHeaders.RECEIVED_PARTITION) int offset, @Header(required = false,value = KafkaHeaders.TOPIC) String topico) {
		LOG.info("Mensaje recibido = [{}]", mensaje.value());
	}
}
