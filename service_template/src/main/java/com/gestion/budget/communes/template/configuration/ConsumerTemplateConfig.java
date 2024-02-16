package com.gestion.budget.communes.template.configuration;

import java.util.function.Function;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gestion.budget.communes.event.EventBudget;
import com.gestion.budget.communes.event.EventTemplate;
import com.gestion.budget.communes.event.StatusBudget;
import com.gestion.budget.communes.template.service.ServiceTemplate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class ConsumerTemplateConfig {
	@Autowired
    private ServiceTemplate serviceTemplate ;

	@Bean
    public Function<Flux<EventBudget>, Flux<EventTemplate>> templateProcessor() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processTemplate);
    }

    private Mono<EventTemplate> processTemplate(EventBudget budgetEvent) {
        /**
         * // get the department id
         * // check the balance availability
         * // if balance sufficient -> template created and deduct nbressource from DB
         * // if nbressource not sufficient -> cancel budget event and update the nbressource in DB
         **/
        if(StatusBudget.BUDGET_DEFINED.equals(budgetEvent.getStatusBudget())){
            return  Mono.fromSupplier(()->this.serviceTemplate.newProjectEvent(budgetEvent));
        }else{
            return Mono.fromRunnable(()->this.serviceTemplate.SuppBudgetEvent(budgetEvent));
        }
    }
}
