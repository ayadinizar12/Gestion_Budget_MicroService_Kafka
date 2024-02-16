package com.gestion.budget.communes.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.budget.communes.DTO.ManagerRequestDTO;
import com.gestion.budget.communes.event.StatusBudget;
import com.gestion.budget.communes.manager.composant.ProducerNotif;
import com.gestion.budget.communes.manager.entite.GradeRessource;
import com.gestion.budget.communes.manager.entite.Notification;
import com.gestion.budget.communes.manager.entite.Projet;
import com.gestion.budget.communes.manager.exception.MapperException;
import com.gestion.budget.communes.manager.repository.GradeRepo;
import com.gestion.budget.communes.manager.repository.ProjetRepo;

import jakarta.transaction.Transactional;


@Service
public class ServiceManager {

	private static final ObjectMapper mapper=new ObjectMapper();
	@Autowired
	private GradeRepo gradeRepo;
	@Autowired
	private ProjetRepo projetRepo;
	@Autowired
	private BudgetStatusPublish budgetStatusPublish;
	
	@Transactional
	public Projet Addprojet (ManagerRequestDTO managerRequestDTO) {
		Projet newprojet= new Projet();
		GradeRessource newListeRessource=new GradeRessource();
		if(managerRequestDTO.getBudget() > 1000.0){
            newprojet = projetRepo.save(convertDtoToEntity(managerRequestDTO));
            managerRequestDTO.setProjetId(newprojet.getId());
		
            newListeRessource=gradeRepo.save(convertDtoToEntity(managerRequestDTO));
         
            budgetStatusPublish.publishEventBudget(managerRequestDTO,StatusBudget.BUDGET_DEFINED);
		}
        else {
            projetRepo.delete(convertDtoToEntity(managerRequestDTO));
            budgetStatusPublish.publishEventBudget(managerRequestDTO,StatusBudget.BUDGET_NOT_DEFINED);
        }
        return newprojet;
    }
	
	private Projet convertDtoToEntity(ManagerRequestDTO DTO) {
        Projet  projet = new Projet();
        projet.setClient(DTO.getClient());
        projet.setProjetName(DTO.getProjetName());
        projet.setBudget(DTO.getBudget());
        projet.setHomme(DTO.getHomme());
        projet.setJours(DTO.getJours());
        projet.setDelivery(DTO.getDelivery());
        projet.setStatusBudget(StatusBudget.BUDGET_DEFINED);
        System.out.println(DTO.getProjetName());
        return projet;
    }
	
	private GradeRessource convertToEntity(ManagerRequestDTO dto){
        GradeRessource gradeRessource = new GradeRessource();
        gradeRessource.setProjetId(dto.getProjetId());
        gradeRessource.setHomme(dto.getHomme());
        gradeRessource.setDeveloper(dto.getDeveloper());
        gradeRessource.setQuality(dto.getQuality());
        gradeRessource.setDevops(dto.getDevops());
        gradeRessource.setSupport(dto.getSupport());
        return gradeRessource;
    }
	
	public void envoie(Notification notification) {
		ProducerNotif.sendNotification(toJson(notification));
	}

	private <T> String toJson(T object) {
	        try {
	            return mapper.writeValueAsString(object);
	        } catch (Exception e) {
	            throw new MapperException(e.getMessage());
	        }
	}
	




}
