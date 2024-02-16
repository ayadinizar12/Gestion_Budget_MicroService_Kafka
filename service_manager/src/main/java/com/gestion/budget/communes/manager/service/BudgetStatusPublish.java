package com.gestion.budget.communes.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.budget.communes.DTO.ManagerRequestDTO;
import com.gestion.budget.communes.event.EventBudget;
import com.gestion.budget.communes.event.StatusBudget;

import reactor.core.publisher.Sinks;

@Service
public class BudgetStatusPublish {

	@Autowired
	private Sinks.Many<EventBudget> sinksbudget;
	
	public void publishEventBudget(ManagerRequestDTO managerResquestDTO, StatusBudget budgetStatus) {
		EventBudget eventBudget=new EventBudget(managerResquestDTO, budgetStatus);
		sinksbudget.tryEmitNext(eventBudget);
	}
	
	
}
