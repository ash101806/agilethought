package com.ashleyzapien.kafkapractice.conf;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConfiguration {
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	@Value("${app.topic.groupid}")
	private String groupid;
	
	@Bean
	public Map<String,Object> producerConfigs(){
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,  StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,  StringSerializer.class);
		props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,  CommonClientConfigs.DEFAULT_SECURITY_PROTOCOL);
		return props;
	}
	
	@Bean
	public Map<String,Object> consumerConfigs(){
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,  StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,  JsonDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG,  groupid);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,  "latest");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,  false);
		props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,  CommonClientConfigs.DEFAULT_SECURITY_PROTOCOL);
		return props;
	}
	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> listenerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
 }
