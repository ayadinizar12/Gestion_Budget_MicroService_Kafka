package com.gestion.budget.communes.manager.configuration;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gestion.budget.communes.event.EventBudget;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Configuration
public class BudgetPublishConfig {
	@Bean
	public Sinks.Many<EventBudget> budgetSinks(){
		return Sinks.many().multicast().onBackpressureBuffer();
	}

	@Bean
	public Supplier<Flux<EventBudget>> budgetSupplier (Sinks.Many<EventBudget> sinks){
		return sinks::asFlux;
	}
	
}