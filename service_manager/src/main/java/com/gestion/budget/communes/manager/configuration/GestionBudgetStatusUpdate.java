package com.gestion.budget.communes.manager.configuration;

import java.util.function.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gestion.budget.communes.DTO.ManagerRequestDTO;
import com.gestion.budget.communes.event.StatusBudget;
import com.gestion.budget.communes.event.StatusTemplate;
import com.gestion.budget.communes.manager.entite.Notification;
import com.gestion.budget.communes.manager.entite.NotificationType;
import com.gestion.budget.communes.manager.entite.Projet;
import com.gestion.budget.communes.manager.repository.NotificationRepo;
import com.gestion.budget.communes.manager.repository.ProjetRepo;
import com.gestion.budget.communes.manager.service.BudgetStatusPublish;
import com.gestion.budget.communes.manager.service.ServiceManager;

import jakarta.transaction.Transactional;
import reactor.core.publisher.*;

@Configuration
public class GestionBudgetStatusUpdate {

	@Autowired
    private ServiceManager serviceManager;
    @Autowired
    private ProjetRepo projetRepo ;
    @Autowired
    private BudgetStatusPublish budgetStatusPublish ;
    public static Double sumSalary;
    
    @Transactional
    public void updateProjet(int id,Consumer<Projet> consumer) {
        projetRepo.findById(id).ifPresent(consumer.andThen(this::updateProjet));
    }
    
    private void updateProjet(Projet projet)  {
        System.out.println("somme : "+sumSalary);
        boolean isTemplateCreated = StatusTemplate.TEMPLATE_CREATED.equals(projet.getStatusTemplate());
        System.out.println("Manager 1 : " + " " + projet.getStatusTemplate());
        StatusBudget budgetStatus = isTemplateCreated ? StatusBudget.BUDGET_SUFFICIENT : StatusBudget.BUDGET_INSUFFICIENT;
        System.out.println("Manager 2 : " + " " + budgetStatus);
        projet.setStatusBudget(budgetStatus);
        if (projet.getBudget() < sumSalary) {
            //repository.delete(project);
            projet.setStatusTemplate(null);
            projet.setStatusBudget(budgetStatus);

            Notification notification = new Notification(
            		projet.getId(),
            		projet.getClient(),
            		"Budget insufficient\uD83D\uDE21 You must make other budget more than your sum salary of your project employees Sum salary = "+sumSalary+ "."
                    , NotificationType.WARN);
            serviceManager.envoie(notification);
            //Notification notification1 = new Notification(project.getId(),project.getClient(),
              //      "Budget insufficient\uD83D\uDE21 You must make other budget more than your sum salary of your project employees Sum salary = "+sumSalary+ "."
                //    , NotificationType.WARN);
          //  notificationRepo.save(notification1);

        } 
        else {	
        	Notification notification = new Notification(
        			projet.getId(), 
        			projet.getClient(), 
        			"Your project was created successfully with Budget"+" "+projet.getBudget()+" "+"and Id "+" "+projet.getId()+" \uD83D\uDE09✌\uD83D\uDC4F️",
        			NotificationType.INFO);
            serviceManager.envoie(notification);
            //Notification notification1 = new Notification(project.getId(),project.getClient(), "Your project was created successfully with Budget"+" "+project.getBudget()+" "+"and Id "+" "+project.getId()+" \uD83D\uDE09✌\uD83D\uDC4F️",NotificationType.INFO);
           // notificationRepo.save(notification1);
        }
            if (!isTemplateCreated) {
                budgetStatusPublish.publishEventBudget(convertEntityToDto(projet),budgetStatus);
            }
        }
    
    public ManagerRequestDTO convertEntityToDto(Projet projet) {
        ManagerRequestDTO managerRequestDTO = new ManagerRequestDTO();
        managerRequestDTO.setProjetId(projet.getId());
        managerRequestDTO.setClient(projet.getClient());
        managerRequestDTO.setProjetName(projet.getProjetName());
        managerRequestDTO.setHomme(projet.getHomme());
        managerRequestDTO.setJours(projet.getJours());
        managerRequestDTO.setDelivery(projet.getDelivery());
        managerRequestDTO.setBudget(projet.getBudget());
        return managerRequestDTO;
    }
    
    
    
    
    
    
    
    
}
