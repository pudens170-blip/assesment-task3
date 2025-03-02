package com.mint.assessmenttask.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.mint.assessmenttask.config.model.CardPojo;

@EnableKafka
@Configuration
public class KafkaConfig {
	
	@Value("${server_config}")
	private String serverConfig;	

	@Bean
	public ConsumerFactory<String, CardPojo> cardConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serverConfig);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				new JsonDeserializer<>(CardPojo.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CardPojo> cardKafkaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, CardPojo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(cardConsumerFactory());
		return factory;
	}
	
	
}
