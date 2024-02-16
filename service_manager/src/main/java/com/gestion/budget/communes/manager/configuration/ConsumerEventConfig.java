package com.gestion.budget.communes.manager.configuration;


import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gestion.budget.communes.event.EventTemplate;

@Configuration
public class ConsumerEventConfig {
	@Autowired
    private GestionBudgetStatusUpdate gestion ;


    @Bean
    public Consumer<EventTemplate> templateConsumerEvent(){
        //listen template-event-topic
        //will check template status
        //if  template status created -> define the budget and create project
        //if template  status failed -> cancel the project
        return (template)-> gestion.updateProjet(template.getTemplateRequestDTO().getProjetId(),
        		tb->{
        			tb.setStatusTemplate(template.getStatusTemplate());
        			}
        		);
    }
}
