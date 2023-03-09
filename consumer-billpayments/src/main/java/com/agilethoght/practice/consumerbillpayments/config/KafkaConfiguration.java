package com.agilethoght.practice.consumerbillpayments.config;


import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.agilethoght.practice.consumerbillpayments.dao.BillPayment;

@EnableKafka
@Configuration
public class KafkaConfiguration {
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	@Value("${app.topic.groupid}")
	private String groupid;
	
	@Bean
	public Map<String,Object> consumerConfigs(){
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG,  groupid);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,  "latest");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,  false);
		props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,  CommonClientConfigs.DEFAULT_SECURITY_PROTOCOL);
		return props;
	}
	@Bean
	public ConsumerFactory<String, BillPayment> consumerFactory() {
		JsonDeserializer<BillPayment> deserializer = new JsonDeserializer<>(BillPayment.class, false);
		//deserializer.addTrustedPackages("*");
		return new DefaultKafkaConsumerFactory<String, BillPayment>(consumerConfigs(), new StringDeserializer(),deserializer);
	}
	@Bean("containerListener")
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, BillPayment>> listenerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, BillPayment> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
 }
