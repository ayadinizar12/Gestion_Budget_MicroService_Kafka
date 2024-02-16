package com.gestion.budget.communes.controle.entite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@SpringBootApplication
public class ServiceContole {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idFacture;
    private Integer projetId;
    private Double realizedJH;
    private Double projetCharge;
    //private Double gainFacture;
}
