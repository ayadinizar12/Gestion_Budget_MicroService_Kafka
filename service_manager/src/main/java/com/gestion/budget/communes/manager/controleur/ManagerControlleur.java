package com.gestion.budget.communes.manager.controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.budget.communes.DTO.ManagerRequestDTO;
import com.gestion.budget.communes.manager.entite.GradeRessource;
import com.gestion.budget.communes.manager.entite.Notification;
import com.gestion.budget.communes.manager.entite.Projet;
import com.gestion.budget.communes.manager.repository.GradeRepo;
import com.gestion.budget.communes.manager.repository.NotificationRepo;
import com.gestion.budget.communes.manager.repository.ProjetRepo;
import com.gestion.budget.communes.manager.service.ServiceManager;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/manager")
@RestController
public class ManagerControlleur {
	    
		@Autowired
		private ServiceManager serviceManager;
	    @Autowired
	    private GradeRepo gradeRepo;
	    @Autowired
	    private ProjetRepo projetRepo;
	    @Autowired
	    private NotificationRepo notificationRepo;

	    @PostMapping("/projet")
	    public Projet AddProjet(ManagerRequestDTO managerRequestDTO) {
	        return serviceManager.Addprojet(managerRequestDTO);
	    }

	    @GetMapping("/projets")
	    public List<Projet> getProjets() {
	        return projetRepo.findAll();
	    }

	    @GetMapping("/grades")
	    public List<GradeRessource> getListRessources() {
	        return gradeRepo.findAll();
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
