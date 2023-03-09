package com.ashleyzapien.kafkapractice.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerImpl implements MessageProducer {
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	@Override
	public void send(String line) {
		String topic = "messages-" + line.trim().split(" ")[0].replace("@", "");
		kafkaTemplate.send(topic.trim(), line.trim().split(" ")[0].replace("@", ""), line.replace(line.trim().split(" ")[0], ""));
	}

}
