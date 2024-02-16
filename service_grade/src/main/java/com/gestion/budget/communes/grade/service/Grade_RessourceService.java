package com.gestion.budget.communes.grade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.budget.communes.DTO.GradeCostRequestDTO;
import com.gestion.budget.communes.DTO.ManagerRequestDTO;
import com.gestion.budget.communes.event.EventBudget;
import com.gestion.budget.communes.event.EventGradeCost;
import com.gestion.budget.communes.event.StatusGradeCost;
import com.gestion.budget.communes.grade.composant.ProducerNotif;
import com.gestion.budget.communes.grade.entite.GradeSalairesProjet;
import com.gestion.budget.communes.grade.entite.Grade_Ressource;
import com.gestion.budget.communes.grade.entite.Notification;
import com.gestion.budget.communes.grade.entite.NotificationType;
import com.gestion.budget.communes.grade.exception.MapperException;
import com.gestion.budget.communes.grade.repository.GradeSalairesProjetRepo;
import com.gestion.budget.communes.grade.repository.Grade_RessourceRepo;
import com.gestion.budget.communes.grade.repository.NotificationRepo;
import com.gestion.budget.communes.grade.repository.SumSalairesProjetRepo;


import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jakarta.transaction.Transactional;

@Service
public class Grade_RessourceService {

	private static final ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private Grade_RessourceRepo grade_RessourceRepo;
	@Autowired
	private GradeSalairesProjetRepo gradeSalairesProjetRepo;
	@Autowired
	private NotificationRepo notificationRepo;
	@Autowired
	private SumSalairesProjetRepo sumSalairesProjetRepo;

	Grade_Ressource GradeResDev=new Grade_Ressource();
	public  Double sommeDev;
	Grade_Ressource GradeResDevops=new Grade_Ressource();
	public  Double sommeDevops;
	Grade_Ressource GradeResSup=new Grade_Ressource();
	public  Double sommeSup;
	Grade_Ressource GradeResQa=new Grade_Ressource();
	public  Double sommeQa;

	
	
	
	public List<GradeSalairesProjet> getAllCOSTRES(){
		return gradeSalairesProjetRepo.findAll();
	}
	
	public Optional<Grade_Ressource> findByIdDev(Integer id){
		Optional<Grade_Ressource> GradeRessource;
		GradeRessource= grade_RessourceRepo.findById(id);
			BeanUtils.copyProperties(GradeRessource.get(), GradeResDev);
			System.out.println(GradeResDev.getGradeId());
			System.out.println(GradeResDev.getSalaire());
			System.out.println(GradeResDev.getDateUpDate());
			return Optional.ofNullable(GradeResDev);
	}
	
	public Optional<Grade_Ressource> findByIdDevops(Integer id){
		Optional<Grade_Ressource> GradeRessource;
		GradeRessource= grade_RessourceRepo.findById(id);
			BeanUtils.copyProperties(GradeRessource.get(), GradeResDevops);
			System.out.println(GradeResDevops.getGradeId());
			System.out.println(GradeResDevops.getSalaire());
			System.out.println(GradeResDevops.getDateUpDate());
			return Optional.ofNullable(GradeResDevops);
	}

	public Optional<Grade_Ressource> findByIdSup(Integer id){
		Optional<Grade_Ressource> GradeRessource;
		GradeRessource= grade_RessourceRepo.findById(id);
			BeanUtils.copyProperties(GradeRessource.get(), GradeResSup);
			System.out.println(GradeResSup.getGradeId());
			System.out.println(GradeResSup.getSalaire());
			System.out.println(GradeResSup.getDateUpDate());
			return Optional.ofNullable(GradeResSup);
	}
	
	public Optional<Grade_Ressource> findByIdQa(Integer id){
		Optional<Grade_Ressource> GradeRessource;
		GradeRessource= grade_RessourceRepo.findById(id);
			BeanUtils.copyProperties(GradeRessource.get(), GradeResQa);
			System.out.println(GradeResQa.getGradeId());
			System.out.println(GradeResQa.getSalaire());
			System.out.println(GradeResQa.getDateUpDate());
			return Optional.ofNullable(GradeResQa);
	}

	@Transactional
    public void SuppNumberResEvent(EventBudget budgetEvent){
        System.out.println("error with budget event");

        gradeSalairesProjetRepo.findById(budgetEvent.getManagerRequestDTO().getProjetId())
                .ifPresent(tr ->{
                	gradeSalairesProjetRepo.delete(tr);
        gradeSalairesProjetRepo.findById(tr.getProjetId())
                            .ifPresent(rb-> rb.setNombre(rb.getNombre()));

                			});

    }
	
	@Transactional
	public EventGradeCost NewEventCostDev(EventBudget eventBudget) {
		ManagerRequestDTO managerRequestDTO=eventBudget.getManagerRequestDTO();
		GradeCostRequestDTO gradeCostRequestDTO = new GradeCostRequestDTO(
				managerRequestDTO.getProjetId(),
                GradeResDev.getGrade(),
                GradeResDev.getSalaire(),
                managerRequestDTO.getDeveloper());
        sommeDev = gradeCostRequestDTO.getSalaire() * gradeCostRequestDTO.getNombre();
        
        EventGradeCost eventGradeCostDev = new EventGradeCost(
        		gradeCostRequestDTO, StatusGradeCost.GRADE_COST_DEFINED);
        
        GradeSalairesProjet gradeSalairesProjet = new GradeSalairesProjet(
        		managerRequestDTO.getProjetId(),
                gradeCostRequestDTO.getGrade(), 
                gradeCostRequestDTO.getSalaire(), 
                gradeCostRequestDTO.getNombre(),
                sommeDev,
                eventGradeCostDev.getStatusGradeCost());
        gradeSalairesProjetRepo.save(gradeSalairesProjet);
        
        Notification notification = new Notification(
        		managerRequestDTO.getProjetId(),
        		managerRequestDTO.getClient(),
                "The last date of update The employees salary is"+" "+GradeResDev.getDateUpDate()
                +" "+"Please contact Comptroller for more information ", NotificationType.INFO);
        envoie(notification);
        
        //Notification notification1 = new Notification(managerRequestDto.getProjectId(),managerRequestDto.getClient(),
          //      "The last date of update The employees salary is"+" "+gradeRes.getDateUpdate()+" "+"Please contact Comptroller for more information ", NotificationType.INFO);
        //notificationRepository.save(notification1);

        return eventGradeCostDev;
	}
	
	@Transactional
	public EventGradeCost NewEventCostDevops(EventBudget eventBudget) {
		ManagerRequestDTO managerRequestDTO=eventBudget.getManagerRequestDTO();
		GradeCostRequestDTO gradeCostRequestDTO = new GradeCostRequestDTO(
				managerRequestDTO.getProjetId(),
                GradeResDevops.getGrade(),
                GradeResDevops.getSalaire(),
                managerRequestDTO.getDevops());
        sommeDevops = gradeCostRequestDTO.getSalaire() * gradeCostRequestDTO.getNombre();
        
        EventGradeCost eventGradeCostDevops = new EventGradeCost(
        		gradeCostRequestDTO, StatusGradeCost.GRADE_COST_DEFINED);
        
        GradeSalairesProjet gradeSalairesProjet = new GradeSalairesProjet(
        		managerRequestDTO.getProjetId(),
                gradeCostRequestDTO.getGrade(), 
                gradeCostRequestDTO.getSalaire(), 
                gradeCostRequestDTO.getNombre(),
                sommeDevops,
                eventGradeCostDevops.getStatusGradeCost());
        gradeSalairesProjetRepo.save(gradeSalairesProjet);
        
        Notification notification = new Notification(
        		managerRequestDTO.getProjetId(),
        		managerRequestDTO.getClient(),
                "The last date of update The employees salary is"+" "+GradeResDevops.getDateUpDate()
                +" "+"Please contact Comptroller for more information ", NotificationType.INFO);
        envoie(notification);
        
        //Notification notification1 = new Notification(managerRequestDto.getProjectId(),managerRequestDto.getClient(),
          //      "The last date of update The employees salary is"+" "+gradeRes.getDateUpdate()+" "+"Please contact Comptroller for more information ", NotificationType.INFO);
        //notificationRepository.save(notification1);

        return eventGradeCostDevops;
	}
	
	@Transactional
	public EventGradeCost NewEventCostSup(EventBudget eventBudget) {
		ManagerRequestDTO managerRequestDTO=eventBudget.getManagerRequestDTO();
		GradeCostRequestDTO gradeCostRequestDTO = new GradeCostRequestDTO(
				managerRequestDTO.getProjetId(),
                GradeResSup.getGrade(),
                GradeResSup.getSalaire(),
                managerRequestDTO.getSupport());
        sommeSup = gradeCostRequestDTO.getSalaire() * gradeCostRequestDTO.getNombre();
        
        EventGradeCost eventGradeCostSup = new EventGradeCost(
        		gradeCostRequestDTO, StatusGradeCost.GRADE_COST_DEFINED);
        
        GradeSalairesProjet gradeSalairesProjet = new GradeSalairesProjet(
        		managerRequestDTO.getProjetId(),
                gradeCostRequestDTO.getGrade(), 
                gradeCostRequestDTO.getSalaire(), 
                gradeCostRequestDTO.getNombre(),
                sommeSup,
                eventGradeCostSup.getStatusGradeCost());
        gradeSalairesProjetRepo.save(gradeSalairesProjet);
        
        Notification notification = new Notification(
        		managerRequestDTO.getProjetId(),
        		managerRequestDTO.getClient(),
                "The last date of update The employees salary is"+" "+GradeResSup.getDateUpDate()
                +" "+"Please contact Comptroller for more information ", NotificationType.INFO);
        envoie(notification);
        
        //Notification notification1 = new Notification(managerRequestDto.getProjectId(),managerRequestDto.getClient(),
          //      "The last date of update The employees salary is"+" "+gradeRes.getDateUpdate()+" "+"Please contact Comptroller for more information ", NotificationType.INFO);
        //notificationRepository.save(notification1);

        return eventGradeCostSup;
	}
	
	@Transactional
	public EventGradeCost NewEventCostQa(EventBudget eventBudget) {
		ManagerRequestDTO managerRequestDTO=eventBudget.getManagerRequestDTO();
		GradeCostRequestDTO gradeCostRequestDTO = new GradeCostRequestDTO(
				managerRequestDTO.getProjetId(),
                GradeResQa.getGrade(),
                GradeResQa.getSalaire(),
                managerRequestDTO.getQuality());
        sommeQa = gradeCostRequestDTO.getSalaire() * gradeCostRequestDTO.getNombre();
        
        EventGradeCost eventGradeCostQa = new EventGradeCost(
        		gradeCostRequestDTO, StatusGradeCost.GRADE_COST_DEFINED);
        
        GradeSalairesProjet gradeSalairesProjet = new GradeSalairesProjet(
        		managerRequestDTO.getProjetId(),
                gradeCostRequestDTO.getGrade(), 
                gradeCostRequestDTO.getSalaire(), 
                gradeCostRequestDTO.getNombre(),
                sommeQa,
                eventGradeCostQa.getStatusGradeCost());
        gradeSalairesProjetRepo.save(gradeSalairesProjet);
        
        Notification notification = new Notification(
        		managerRequestDTO.getProjetId(),
        		managerRequestDTO.getClient(),
                "The last date of update The employees salary is"+" "+GradeResQa.getDateUpDate()
                +" "+"Please contact Comptroller for more information ", NotificationType.INFO);
        envoie(notification);
        
        //Notification notification1 = new Notification(managerRequestDto.getProjectId(),managerRequestDto.getClient(),
          //      "The last date of update The employees salary is"+" "+gradeRes.getDateUpdate()+" "+"Please contact Comptroller for more information ", NotificationType.INFO);
        //notificationRepository.save(notification1);

        return eventGradeCostQa;
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
