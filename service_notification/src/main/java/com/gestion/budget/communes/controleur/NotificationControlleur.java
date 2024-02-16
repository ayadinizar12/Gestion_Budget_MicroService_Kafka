package com.gestion.budget.communes.controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.budget.communes.entite.Notification;
import com.gestion.budget.communes.repository.NotificationRepo;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/notifications")
@RestController
public class NotificationControlleur {
	    
	    @Autowired
	    private NotificationRepo notificationRepo;

	    @GetMapping("/manager")
	    public List<Notification> getNotif() {
	        return notificationRepo.findAll();
	    }

	    @GetMapping("/notificationuib")
	    public List<Notification> getNotifuib() {
	        return notificationRepo.findNotificationsByClient("'UIB'");
	    }
	    
	    @GetMapping("/notificationatb")
	    public List<Notification> getNotifAtb() {
	        return notificationRepo.findNotificationsByClient("'ATB'");
	    }

	    @GetMapping("/notificationbna")
	    public List<Notification> getNotifBna() {
	        return notificationRepo.findNotificationsByClient("'BNA'");
	    }
	

	
}
