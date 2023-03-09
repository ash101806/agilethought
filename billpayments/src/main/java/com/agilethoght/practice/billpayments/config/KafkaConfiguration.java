package com.agilethoght.practice.billpayments.config;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.agilethoght.practice.billpayments.models.BillPayment;

@EnableKafka
@Configuration
public class KafkaConfiguration {
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	@Value("${agile.kafka.topic.name}")
	private String topicName;
	@Bean
	public Map<String,Object> producerConfigs(){
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,  StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,  JsonSerializer.class);
		props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,  CommonClientConfigs.DEFAULT_SECURITY_PROTOCOL);
		return props;
	}
	
	@Bean
	public ProducerFactory<String, BillPayment> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public KafkaTemplate<String, BillPayment> kafkaTemplate(){
		KafkaTemplate<String, BillPayment> temp =  new KafkaTemplate<>(producerFactory());
		temp.setDefaultTopic(topicName);
		
		return temp;
	}
 }
