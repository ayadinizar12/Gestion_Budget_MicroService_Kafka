package com.gestion.budget.communes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestion.budget.communes.entite.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Integer>{

	@Query("select c from Notification c where c.client = :client")
	List<Notification> findNotificationsByClient(@Param("client")String client);
	
}