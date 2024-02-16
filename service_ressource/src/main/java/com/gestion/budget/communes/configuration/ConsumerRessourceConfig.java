package com.gestion.budget.communes.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.gestion.budget.communes.event.*;
import com.gestion.budget.communes.service.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Configuration
public class ConsumerRessourceConfig {


	@Autowired
    private ServiceEmploye employeService;


	@Bean
    public Function<Flux<EventBudget>, Flux<EventRessource>> RessourceProcessorDev() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processResDev);
    }
	@Bean
    public Function<Flux<EventBudget>, Flux<EventRessource>> RessourceProcessorDevopes() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processResDevopes);
    }
	@Bean
    public Function<Flux<EventBudget>, Flux<EventRessource>> RessourceProcessorQa() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processResQa);
    }
	@Bean
    public Function<Flux<EventBudget>, Flux<EventRessource>> RessourceProcessorSup() {
        return budgetEventFlux -> budgetEventFlux.flatMap(this::processResSup);
    }

	
	private Mono<EventRessource> processResDev(EventBudget budgetEvent) {

        if(StatusBudget.BUDGET_DEFINED.equals(budgetEvent.getStatusBudget())){
            return  Mono.fromSupplier(()->this.employeService.newEventResDev(budgetEvent)); 

        }else{
            return Mono.fromRunnable(()->this.employeService.SuppNumberResEvent(budgetEvent));
        }
    }
	
	private Mono<EventRessource> processResDevopes(EventBudget budgetEvent) {

        if(StatusBudget.BUDGET_DEFINED.equals(budgetEvent.getStatusBudget())){
            return  Mono.fromSupplier(()-> this.employeService.newEventResDevopes(budgetEvent));

        }else{
            return Mono.fromRunnable(()->this.employeService.SuppNumberResEvent(budgetEvent));
        }
    }
	
	private Mono<EventRessource> processResQa(EventBudget budgetEvent) {

        if(StatusBudget.BUDGET_DEFINED.equals(budgetEvent.getStatusBudget())){
            return  Mono.fromSupplier(()-> this.employeService.newEventResQa(budgetEvent));

        }else{
            return Mono.fromRunnable(()->this.employeService.SuppNumberResEvent(budgetEvent));
        }
    }
	
	private Mono<EventRessource> processResSup(EventBudget budgetEvent) {

        if(StatusBudget.BUDGET_DEFINED.equals(budgetEvent.get .getStatusBudget())){
            return  Mono.fromSupplier(()-> this.employeService.newEventResSup(budgetEvent));

        }else{
            return Mono.fromRunnable(()->this.employeService.SuppNumberResEvent(budgetEvent));
        }
    }

}
