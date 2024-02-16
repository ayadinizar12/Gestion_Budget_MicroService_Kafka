package com.gestion.budget.communes.template.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.budget.communes.DTO.ManagerRequestDTO;
import com.gestion.budget.communes.DTO.TemplateRequestDTO;
import com.gestion.budget.communes.event.EventBudget;
import com.gestion.budget.communes.event.EventTemplate;
import com.gestion.budget.communes.event.StatusTemplate;
import com.gestion.budget.communes.manager.composant.ProducerNotif;
import com.gestion.budget.communes.manager.entite.GradeRessource;
import com.gestion.budget.communes.manager.entite.Notification;
import com.gestion.budget.communes.manager.entite.Projet;
import com.gestion.budget.communes.manager.exception.MapperException;
import com.gestion.budget.communes.manager.repository.GradeRepo;
import com.gestion.budget.communes.manager.repository.ProjetRepo;
import com.gestion.budget.communes.template.entite.Template;
import com.gestion.budget.communes.template.repository.TemplateRepo;

import jakarta.transaction.Transactional;


@Service
public class ServiceTemplate {

	
	@Autowired
	private TemplateRepo templateRepo ;

	public Optional<Template> getTemplateById(int id){
        return templateRepo.findById(id);
    }
    public List<Template> getAllTemplate(){
        return templateRepo.findAll();
    }
 
    /**
     * // get the department id
     * // check the balance availability
     * // if balance sufficient -> template created and deduct nbressource from DB
     * // if nbressource not sufficient -> cancel budget event and update the nbressource in DB
     **/
    
    
    
    @Transactional
    public EventTemplate newProjectEvent(EventBudget eventBudget ){
       // Template template =new Template();

        ManagerRequestDTO managerRequestDTO = eventBudget.getManagerRequestDTO();
        TemplateRequestDTO templateRequestDTO = new TemplateRequestDTO(
        		managerRequestDTO.getClient(),
        		managerRequestDTO.getProjetName(),
        		managerRequestDTO.getBudget(),
        		managerRequestDTO.getHomme(),
        		managerRequestDTO.getJours(),
        		managerRequestDTO.getDelivery(),
        		managerRequestDTO.getProjetId());
        LocalDate start = LocalDate.now();
        System.out.println("Template : "+" "+eventBudget.getStatusBudget());
        String end1 = managerRequestDTO.getDelivery().substring(1,11);
        LocalDate endDate = LocalDate.parse(end1);


        long monthsBetween = ChronoUnit.MONTHS.between(
                start.withDayOfMonth(1),
                endDate.withDayOfMonth(1));
        System.out.println(monthsBetween);
        Double budgetMonthly = managerRequestDTO.getBudget()/monthsBetween;
        if(managerRequestDTO.getBudget() > 100.00){
            EventTemplate templateEvent = new EventTemplate(
            		templateRequestDTO,
            		StatusTemplate.TEMPLATE_CREATED);
            
            templateRepo.save(new Template(
            		managerRequestDTO.getProjetId(),
            		managerRequestDTO.getClient(),
            		managerRequestDTO.getProjetName(),
                    managerRequestDTO.getBudget(),
                    managerRequestDTO.getHomme(),
                    managerRequestDTO.getJours(),
                    managerRequestDTO.getDelivery(),
                    budgetMonthly,
                    templateEvent.getStatusTemplate()));
            return templateEvent; // new TemplateEvent(templateRequestDto, TemplateStatus.TEMPLATE_CREATED);
        }
        else {
            EventTemplate templateEvent  = new EventTemplate(templateRequestDTO ,StatusTemplate.TEMPLATE_FAILED);

            return templateEvent;//new TemplateEvent(templateRequestDto, TemplateStatus.TEMPLATE_FAILED);
        }


/*
       return templateRepository.findById(managerRequestDto.getProjectId())
               .filter(rb-> rb.getBudget() > 1000.00)
               .map(rb -> {
                   rb.setBudget(managerRequestDto.getBudget());
                   templateRepository.save(new Template(managerRequestDto.getProjectId(),
                           managerRequestDto.getManagerId(), managerRequestDto.getBudget()));
                   return new TemplateEvent(templateRequestDto, TemplateStatus.TEMPLATE_CREATED);
               }).orElse(
                       new TemplateEvent(templateRequestDto, TemplateStatus.TEMPLATE_FAILED)
               );
*/

    }

    @Transactional
    public void SuppBudgetEvent(EventBudget budgetEvent){

        templateRepo.findById(budgetEvent.getManagerRequestDTO().getProjetId())
                .ifPresent(tr ->{
                    templateRepo.delete(tr);
                    templateRepo.findById(tr.getProjetId())
                            .ifPresent(rb-> rb.setBudget(rb.getBudget()));
                });

    }

}