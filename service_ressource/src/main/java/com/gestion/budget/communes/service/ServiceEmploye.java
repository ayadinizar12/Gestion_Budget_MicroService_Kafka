package com.gestion.budget.communes.service;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.budget.communes.DTO.EmployeRequestDTO;
import com.gestion.budget.communes.DTO.ManagerRequestDTO;
import com.gestion.budget.communes.DTO.RessourceRequestDTO;
import com.gestion.budget.communes.composant.ProducerNotif;
import com.gestion.budget.communes.entite.Employe;
import com.gestion.budget.communes.entite.Notification;
import com.gestion.budget.communes.entite.NotificationType;
import com.gestion.budget.communes.entite.Ressource;
import com.gestion.budget.communes.event.EventBudget;
import com.gestion.budget.communes.event.EventRessource;
import com.gestion.budget.communes.event.StatusEmploye;
import com.gestion.budget.communes.event.StatusRessource;
import com.gestion.budget.communes.exception.MapperException;
import com.gestion.budget.communes.repository.EmployeRepo;
import com.gestion.budget.communes.repository.NotificationRepo;
import com.gestion.budget.communes.repository.RessourceRepo;

import jakarta.transaction.Transactional;



@Service
public class ServiceEmploye {

	private static final ObjectMapper mapper=new ObjectMapper();
	@Autowired
	private NotificationRepo notificationRepo;
	@Autowired
	private EmployeRepo employeRepo;
    @Autowired
    private RessourceRepo ressourceRepo;
    LocalDate start = LocalDate.now();

    
    @Transactional
	public Employe AddEmploye(EmployeRequestDTO employeRequestDTO) {
        System.out.println(employeRequestDTO.getFullName());
        System.out.println(employeRequestDTO.getGrade());
        Employe employe = new Employe();
        employe = employeRepo.save(convertDtoToEntity(employeRequestDTO));
        employeRequestDTO.setIdEmploye(employe.getIdEmployee());
        return employe;
	}
	
	@Transactional
	public void SuppNumberResEvent(EventBudget eventBudget){
        System.out.println("error with budget event");
        ressourceRepo.findById(eventBudget.getManagerRequestDTO().getProjetId())
        .ifPresent(tr ->{
        	ressourceRepo.delete(tr);
        	ressourceRepo.findById(tr.getProjetId())
        .ifPresent(rb-> rb.setEmployeNumber(rb.getEmployeNumber()));
        	});
        }
	
	@Transactional
    public EventRessource newEventResDev(EventBudget eventBudget) {
        String grade = "developer";
        ManagerRequestDTO managerRequestDTO = eventBudget.getManagerRequestDTO();
        RessourceRequestDTO ressourceRequestDTO = new RessourceRequestDTO(
        		managerRequestDTO.getProjetId(),
                start,
                managerRequestDTO.getDelivery(),
                grade,
                managerRequestDTO.getDeveloper());
        
        Boolean isEmployeeExist = getEmployeesByGrade(ressourceRequestDTO);
        System.out.println(isEmployeeExist);
        EventRessource eventRessource = new EventRessource(ressourceRequestDTO, StatusRessource.RESOUCRE_AFFECTED);
        if(isEmployeeExist & managerRequestDTO.getDeveloper() != 0) {
            Ressource ressource = new Ressource(
            		ressourceRequestDTO.getProjetId(),
                    ressourceRequestDTO.getStartDate(),
                    ressourceRequestDTO.getEndDate(),
                    ressourceRequestDTO.getGradeRessource(),
                    ressourceRequestDTO.getEmployeeNumber(),
                    eventRessource.getStatusRessource());
            		ressourceRepo.save(ressource);
            		
            Notification notification = new Notification(
            		ressourceRequestDTO.getProjetId(),
            		managerRequestDTO.getClient(),
                    "Developers affected to employees with success ", 
                    NotificationType.INFO);
            envoie(notification);
            
/*            Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                    "Developers affected to employees with success ", NotificationType.INFO);
            notificationRepository.save(notification1);*/
        
        }else {
            if (managerRequestDTO.getDeveloper() != 0) {
                Notification notification = new Notification(
                		ressourceRequestDTO.getProjetId(), 
                		managerRequestDTO.getClient(),
                        "Sorry the number of developer doesn't exist. We must be recruit other Developers! Contact HR for more information", 
                        NotificationType.WARN);
                envoie(notification);
                
/*                Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                        "Sorry the number of developer doesn't exist. We must be recruit other Developers! Contact HR for more information", NotificationType.WARN);

                notificationRepository.save(notification1);*/
            }
        }
            return eventRessource;

    }
	
	
	
	@Transactional
    public EventRessource newEventResDevopes(EventBudget eventBudget) {
        String grade = "devopes";
        ManagerRequestDTO managerRequestDTO = eventBudget.getManagerRequestDTO();
        RessourceRequestDTO ressourceRequestDTO = new RessourceRequestDTO(
        		managerRequestDTO.getProjetId(),
                start,
                managerRequestDTO.getDelivery(),
                grade,
                managerRequestDTO.getDevops());
        
        Boolean isEmployeeExist = getEmployeesByGrade(ressourceRequestDTO);
        System.out.println(isEmployeeExist);
        EventRessource eventRessource = new EventRessource(ressourceRequestDTO, StatusRessource.RESOUCRE_AFFECTED);
        if(isEmployeeExist & managerRequestDTO.getDevops() != 0) {
            Ressource ressource = new Ressource(
            		ressourceRequestDTO.getProjetId(),
                    ressourceRequestDTO.getStartDate(),
                    ressourceRequestDTO.getEndDate(),
                    ressourceRequestDTO.getGradeRessource(),
                    ressourceRequestDTO.getEmployeeNumber(),
                    eventRessource.getStatusRessource());
            		ressourceRepo.save(ressource);
            		
            Notification notification = new Notification(
            		ressourceRequestDTO.getProjetId(),
            		managerRequestDTO.getClient(),
                    "Devopes affected to employees with success ", 
                    NotificationType.INFO);
            envoie(notification);
            
/*            Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                    "Developers affected to employees with success ", NotificationType.INFO);
            notificationRepository.save(notification1);*/
        
        }else {
            if (managerRequestDTO.getDevops() != 0) {
                Notification notification = new Notification(
                		ressourceRequestDTO.getProjetId(), 
                		managerRequestDTO.getClient(),
                        "Sorry the number of devopes doesn't exist. We must be recruit other Developers! Contact HR for more information", 
                        NotificationType.WARN);
                envoie(notification);
                
/*                Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                        "Sorry the number of developer doesn't exist. We must be recruit other Developers! Contact HR for more information", NotificationType.WARN);

                notificationRepository.save(notification1);*/
            }
        }
            return eventRessource;

    }
	
	
	@Transactional
    public EventRessource newEventResQa(EventBudget eventBudget) {
        String grade = "Quality";
        ManagerRequestDTO managerRequestDTO = eventBudget.getManagerRequestDTO();
        RessourceRequestDTO ressourceRequestDTO = new RessourceRequestDTO(
        		managerRequestDTO.getProjetId(),
                start,
                managerRequestDTO.getDelivery(),
                grade,
                managerRequestDTO.getQuality());
        
        Boolean isEmployeeExist = getEmployeesByGrade(ressourceRequestDTO);
        System.out.println(isEmployeeExist);
        EventRessource eventRessource = new EventRessource(ressourceRequestDTO, StatusRessource.RESOUCRE_AFFECTED);
        if(isEmployeeExist & managerRequestDTO.getQuality() != 0) {
            Ressource ressource = new Ressource(
            		ressourceRequestDTO.getProjetId(),
                    ressourceRequestDTO.getStartDate(),
                    ressourceRequestDTO.getEndDate(),
                    ressourceRequestDTO.getGradeRessource(),
                    ressourceRequestDTO.getEmployeeNumber(),
                    eventRessource.getStatusRessource());
            		ressourceRepo.save(ressource);
            		
            Notification notification = new Notification(
            		ressourceRequestDTO.getProjetId(),
            		managerRequestDTO.getClient(),
                    "Quality affected to employees with success ", 
                    NotificationType.INFO);
            envoie(notification);
            
/*            Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                    "Developers affected to employees with success ", NotificationType.INFO);
            notificationRepository.save(notification1);*/
        
        }else {
            if (managerRequestDTO.getQuality() != 0) {
                Notification notification = new Notification(
                		ressourceRequestDTO.getProjetId(), 
                		managerRequestDTO.getClient(),
                        "Sorry the number of quality doesn't exist. We must be recruit other Developers! Contact HR for more information", 
                        NotificationType.WARN);
                envoie(notification);
                
/*                Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                        "Sorry the number of developer doesn't exist. We must be recruit other Developers! Contact HR for more information", NotificationType.WARN);

                notificationRepository.save(notification1);*/
            }
        }
            return eventRessource;

    }

	
	@Transactional
    public EventRessource newEventResSup(EventBudget eventBudget) {
        String grade = "Support";
        ManagerRequestDTO managerRequestDTO = eventBudget.getManagerRequestDTO();
        RessourceRequestDTO ressourceRequestDTO = new RessourceRequestDTO(
        		managerRequestDTO.getProjetId(),
                start,
                managerRequestDTO.getDelivery(),
                grade,
                managerRequestDTO.getSupport());
        
        Boolean isEmployeeExist = getEmployeesByGrade(ressourceRequestDTO);
        System.out.println(isEmployeeExist);
        EventRessource eventRessource = new EventRessource(ressourceRequestDTO, StatusRessource.RESOUCRE_AFFECTED);
        if(isEmployeeExist & managerRequestDTO.getDevops() != 0) {
            Ressource ressource = new Ressource(
            		ressourceRequestDTO.getProjetId(),
                    ressourceRequestDTO.getStartDate(),
                    ressourceRequestDTO.getEndDate(),
                    ressourceRequestDTO.getGradeRessource(),
                    ressourceRequestDTO.getEmployeeNumber(),
                    eventRessource.getStatusRessource());
            		ressourceRepo.save(ressource);
            		
            Notification notification = new Notification(
            		ressourceRequestDTO.getProjetId(),
            		managerRequestDTO.getClient(),
                    "Support affected to employees with success ", 
                    NotificationType.INFO);
            envoie(notification);
            
/*            Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                    "Developers affected to employees with success ", NotificationType.INFO);
            notificationRepository.save(notification1);*/
        
        }else {
            if (managerRequestDTO.getSupport() != 0) {
                Notification notification = new Notification(
                		ressourceRequestDTO.getProjetId(), 
                		managerRequestDTO.getClient(),
                        "Sorry the number of support doesn't exist. We must be recruit other Developers! Contact HR for more information", 
                        NotificationType.WARN);
                envoie(notification);
                
/*                Notification notification1 = new Notification(ressourceRequestDto.getProjectId(), managerRequestDto.getClient(),
                        "Sorry the number of developer doesn't exist. We must be recruit other Developers! Contact HR for more information", NotificationType.WARN);

                notificationRepository.save(notification1);*/
            }
        }
            return eventRessource;

    }
	
	public Boolean getEmployeesByGrade(RessourceRequestDTO ressourceRequestDTO) {
        List<Employe> employees = employeRepo
                .findEmployeeByGrade(ressourceRequestDTO.getGradeRessource());
        List<Employe> inactiveEmployees = new ArrayList<>();
        employees.forEach(employee -> {
            if (employee.getStatus().equals(StatusEmploye.EMPLOYE_INACTIVE)) {
                //employee.setProjectId(ressourceRequestDto.getProjectId());
                inactiveEmployees.add(employee);
            }
        });
        System.out.println(inactiveEmployees);
        if (ressourceRequestDTO.getEmployeeNumber() <= inactiveEmployees.size() ) {
            List<Employe> employeesAffecté =getRandomEmployees(
            		inactiveEmployees, 
            		ressourceRequestDTO.getEmployeeNumber());
            
            System.out.println(employeesAffecté);
            
            employeesAffecté.forEach(employee -> {
                employee.setProjetId(ressourceRequestDTO.getProjetId());
                
            updateEmployee(employee);
            
            });
            
        System.out.println(employeesAffecté);
        	return true;
        
        }
        else {

        	return false;
        }

    }
    
	public List<Employe> getRandomEmployees(List<Employe> list, int totalItems) {
        Random rand = new Random();
        List<Employe> employeeList = new ArrayList<>();

        for (int i = 0; i < totalItems; i++) {
            int randomIndex = rand.nextInt(list.size());
            employeeList.add(list.get(randomIndex));
            list.remove(randomIndex);
        }
        	return employeeList;
    }
	
	
    public void updateEmployee(Employe employee) {
        employee.setStatus(StatusEmploye.EMPLOYE_ACTIVE);
        employeRepo.saveAndFlush(employee);
    }

	
	
	
    private Employe convertDtoToEntity(EmployeRequestDTO  dto) {
        Employe employe = new Employe();
        employe.setFullName(dto.getFullName());
        employe.setGrade(dto.getGrade());
        employe.setStatus(StatusEmploye.EMPLOYE_INACTIVE);
        return employe;
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

	
	
	

