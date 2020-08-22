package com.mint.assessmenttask.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mint.assessmenttask.config.model.CardPojo;

@Service
public class KafkaConsumer {
	final static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	private final static String topic = "com.ng.vela.even.card_verified";

	@KafkaListener(topics = topic, groupId = "group_json", containerFactory = "cardKafkaListenerFactory")
	public void consumeJson(CardPojo card) {
		logger.info("Consumed message from kafka: " + card);
	}
}
