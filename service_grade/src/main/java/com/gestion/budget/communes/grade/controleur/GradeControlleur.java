package com.gestion.budget.communes.grade.controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.budget.communes.grade.entite.GradeSalairesProjet;
import com.gestion.budget.communes.grade.entite.Notification;
import com.gestion.budget.communes.grade.repository.GradeSalairesProjetRepo;
import com.gestion.budget.communes.grade.repository.Grade_RessourceRepo;
import com.gestion.budget.communes.grade.repository.NotificationRepo;
import com.gestion.budget.communes.grade.service.Grade_RessourceService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/graderessource")
@RestController
public class GradeControlleur {
	    
		@Autowired
		private Grade_RessourceService grade_RessourceService;
	    @Autowired
	    private NotificationRepo notificationRepo;

	    @GetMapping
	    public List<GradeSalairesProjet> getListTemplate() {
	        return grade_RessourceService.getAllCOSTRES();
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
