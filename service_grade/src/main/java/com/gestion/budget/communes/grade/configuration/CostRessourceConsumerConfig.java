package com.gestion.budget.communes.grade.configuration;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gestion.budget.communes.event.EventBudget;
import com.gestion.budget.communes.event.EventGradeCost;
import com.gestion.budget.communes.event.StatusBudget;
import com.gestion.budget.communes.grade.service.Grade_RessourceService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class CostRessourceConsumerConfig {

	@Autowired
	private Grade_RessourceService grade_RessourceService;

	@Bean
	public Function<Flux<EventBudget>, Flux<EventGradeCost>> costRessourceProcessor1() {
		return eventBudgetFlux -> eventBudgetFlux.flatMap(this::processCostRes1);
	}

	@Bean
	public Function<Flux<EventBudget>, Flux<EventGradeCost>> costRessourceProcessor2() {
		return eventBudgetFlux -> eventBudgetFlux.flatMap(this::processCostRes2);
	}
    
	@Bean
	public Function<Flux<EventBudget>, Flux<EventGradeCost>> costRessourceProcessor3() {
		return eventBudgetFlux -> eventBudgetFlux.flatMap(this::processCostRes3);
	}
    
	@Bean
	public Function<Flux<EventBudget>, Flux<EventGradeCost>> costRessourceProcessor4() {
		return eventBudgetFlux -> eventBudgetFlux.flatMap(this::processCostRes4);
	}
    

    private Mono<EventGradeCost> processCostRes1(EventBudget eventBudget) {

        if(StatusBudget.BUDGET_DEFINED.equals(eventBudget.getStatusBudget())){
            return  Mono.fromSupplier(()-> this.grade_RessourceService.NewEventCostDev(eventBudget));

        }else{
            return Mono.fromRunnable(()->this.grade_RessourceService.SuppNumberResEvent(eventBudget));
        	}
    }
    
    private Mono<EventGradeCost> processCostRes2(EventBudget eventBudget) {

        if(StatusBudget.BUDGET_DEFINED.equals(eventBudget.getStatusBudget())){
            return  Mono.fromSupplier(()-> this.grade_RessourceService.NewEventCostDevops(eventBudget));

        }else{
            return Mono.fromRunnable(()->this.grade_RessourceService.SuppNumberResEvent(eventBudget));
        	}
    }
    
    private Mono<EventGradeCost> processCostRes3(EventBudget eventBudget) {

        if(StatusBudget.BUDGET_DEFINED.equals(eventBudget.getStatusBudget())){
            return  Mono.fromSupplier(()-> this.grade_RessourceService.NewEventCostQa(eventBudget));

        }else{
            return Mono.fromRunnable(()->this.grade_RessourceService.SuppNumberResEvent(eventBudget));
        	}
    }
    
    
    private Mono<EventGradeCost> processCostRes4(EventBudget eventBudget) {

        if(StatusBudget.BUDGET_DEFINED.equals(eventBudget.getStatusBudget())){
            return  Mono.fromSupplier(()-> this.grade_RessourceService.NewEventCostSup(eventBudget));

        }else{
            return Mono.fromRunnable(()->this.grade_RessourceService.SuppNumberResEvent(eventBudget));
        	}
    }

}
