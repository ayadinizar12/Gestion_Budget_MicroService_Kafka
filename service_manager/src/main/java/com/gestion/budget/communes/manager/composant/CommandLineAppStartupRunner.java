package com.gestion.budget.communes.manager.composant;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.gestion.budget.communes.DTO.ManagerRequestDTO;
import com.gestion.budget.communes.manager.controleur.ManagerControlleur;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner{

	@Autowired
	ManagerControlleur managerControlleur= new ManagerControlleur();
	
	
	
	@Override
	public void run(String... args) throws Exception {
		ManagerRequestDTO managerRequestDTO = new ManagerRequestDTO();
        System.out.println(managerRequestDTO.getProjetId());
        String topic1 = "data-manager";
        String topic2 = "sumSalary-event";
        
        
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group_id_1");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        System.out.println("Proper Worked");
        
        //creating consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        
        //Subscribing
        consumer.subscribe(Arrays.asList(topic1));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1000));

            for (ConsumerRecord<String, String> data : records) {
                //int k = managerRequestDto.getProjectId();
                //String IdProject = String.valueOf(k);
                System.out.println("consuming Successfully");
                System.out.println(managerRequestDTO.getProjetId());
                //System.out.println("idprojet:"+ IdProjet);
                
                if (data.key().equals("client")) {
                    managerRequestDTO.setClient(data.value());
                    
                } else if (data.key().equals("name")) {
                    managerRequestDTO.setProjetName(data.value());
                    
                } else if (data.key().equals("budget")) {
                    managerRequestDTO.setBudget(Double.parseDouble(data.value()));
                    
                } else if (data.key().equals("personne")) {
                    managerRequestDTO.setHomme(Integer.valueOf(data.value()));
                    
                } else if (data.key().equals("jours")) {
                    managerRequestDTO.setJours(Integer.valueOf(data.value()));
                    
                } else if (data.key().equals("developer")) {
                    managerRequestDTO.setDeveloper(Integer.valueOf(data.value()));
                    
                } else if (data.key().equals("quality")) {
                    managerRequestDTO.setQuality(Integer.valueOf(data.value()));
                    
                } else if (data.key().equals("devops")) {
                    managerRequestDTO.setDevops(Integer.valueOf(data.value()));
                    
                } else if (data.key().equals("support")) {
                    managerRequestDTO.setSupport(Integer.valueOf(data.value()));
                    
                } else if (data.key().equals("deliveryDate")) {
                    managerRequestDTO.setDelivery(data.value());
                    
                    //} else if (data.key().equals(IdProject)) {
                    //  sumSalary = Double.valueOf(data.value());
                    //System.out.println(sumSalary);
                    
                } else {
                    System.out.println("consuming failed");
                }
            }
            System.out.println(managerRequestDTO.getProjetId());
            ManagerRequestDTO managerRequestDto1 = new ManagerRequestDTO(managerRequestDTO.getClient(), 
            		managerRequestDTO.getProjetName(),
                    managerRequestDTO.getBudget(),
                    managerRequestDTO.getHomme(),
                    managerRequestDTO.getJours(), 
                    managerRequestDTO.getDeveloper(),
                    managerRequestDTO.getQuality(), 
                    managerRequestDTO.getDevops(),
                    managerRequestDTO.getSupport(), 
                    managerRequestDTO.getDelivery(),
                    managerRequestDTO.getProjetId());
            
            managerControlleur.AddProjet(managerRequestDto1);
            //consumer.unsubscribe();
            // consumer.wait(30000);

            consumer.subscribe(Arrays.asList(topic1));

            ConsumerRecords<String, String> record = consumer.poll(Duration.ofSeconds(1000));

            for (ConsumerRecord<String, String> data : record) {
                
            	System.out.println("key" + data.key());
                
            	Integer k = managerRequestDto1.getProjetId();
                String IdProjet = String.valueOf(k);
                
                System.out.println("consuming Successfully");
                System.out.println(managerRequestDto1.getProjetId());
                System.out.println("idproject:" + IdProjet);
                
                if (data.key().equals(IdProjet)) {
                    sumSalary = Double.valueOf(data.value());
                    System.out.println(sumSalary);
                } else {
                    System.out.println("consuming failed");
                }
            }


        }
    }
}

