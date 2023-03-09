package com.agilethoght.practice.consumerbillpayments.app;

import javax.transaction.Transactional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.agilethoght.practice.consumerbillpayments.dao.BillPayment;
import com.agilethoght.practice.consumerbillpayments.dao.BillPaymentRepositoryDAO;

@Component
public class MessageConsumer {
	private static final Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);
	@Autowired
	private BillPaymentRepositoryDAO billRepo;

	@KafkaListener(topics = "${app.topic.name}", groupId = "${app.topic.groupid}", containerFactory = "containerListener")
	@Transactional
	public void consume(BillPayment mensaje) {
		try {
			LOG.info("Se recibió el mensje con id: " + mensaje.getId());
			BillPayment record = billRepo.findById(mensaje.getId())
					.orElseThrow(() -> new Exception("The record does not exists"));
			record.setStatus("A");
		} catch (Exception e) {
			LOG.error("Hubo un problema al procesar el mensaje: ", e);
			billRepo.findById(mensaje.getId()).ifPresent(r -> {
				r.setStatus("R");
				billRepo.save(r);
			});
		}

	}
}
