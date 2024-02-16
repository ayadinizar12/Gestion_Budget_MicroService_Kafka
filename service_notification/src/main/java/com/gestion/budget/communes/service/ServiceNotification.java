package com.gestion.budget.communes.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.budget.communes.entite.Notification;
import com.gestion.budget.communes.exception.MapperException;
import com.gestion.budget.communes.repository.NotificationRepo;


@Slf4j
@Component
public class ServiceNotification {

	private static final ObjectMapper mapper=new ObjectMapper();
	@Autowired
	private NotificationRepo notificationRepo;
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@KafkaListener(topics = "event-notification", groupId = "group_id_1")
    public void listenSenderEmail(String data) {
        Notification notification = fromJson(data, Notification.class);
        log.info("Consumed message: " + data);
        messagingTemplate.convertAndSend("/topic/notif", notification);
        notificationRepo.save(notification);
      //messagingTemplate.convertAndSend();

    }

    private <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new MapperException(e.getMessage());
        }
    }
}

	
	
	

