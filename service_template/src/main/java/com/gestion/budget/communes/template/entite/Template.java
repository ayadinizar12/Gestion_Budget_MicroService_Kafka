package com.gestion.budget.communes.template.entite;

import com.gestion.budget.communes.event.StatusTemplate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
public class Template {

    @Id
    private Integer projetId;
    private String client;
    private String projetName;
    private  double budget ;
    private Integer homme;
    private Integer jours;
    private String delivery;
    private double budgetMonthly;
    @Enumerated(EnumType.STRING)
    private StatusTemplate statusTemplate;
	public Integer getProjetId() {
		return projetId;
	}
	public void setProjetId(Integer projetId) {
		this.projetId = projetId;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getProjetName() {
		return projetName;
	}
	public void setProjetName(String projetName) {
		this.projetName = projetName;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public Integer getHomme() {
		return homme;
	}
	public void setHomme(Integer homme) {
		this.homme = homme;
	}
	public Integer getJours() {
		return jours;
	}
	public void setJours(Integer jours) {
		this.jours = jours;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public double getBudgetMonthly() {
		return budgetMonthly;
	}
	public void setBudgetMonthly(double budgetMonthly) {
		this.budgetMonthly = budgetMonthly;
	}
	public StatusTemplate getStatusTemplate() {
		return statusTemplate;
	}
	public void setStatusTemplate(StatusTemplate statusTemplate) {
		this.statusTemplate = statusTemplate;
	}
	public Template(Integer projetId, String client, String projetName, double budget, Integer homme, Integer jours,
			String delivery, double budgetMonthly, StatusTemplate statusTemplate) {
		super();
		this.projetId = projetId;
		this.client = client;
		this.projetName = projetName;
		this.budget = budget;
		this.homme = homme;
		this.jours = jours;
		this.delivery = delivery;
		this.budgetMonthly = budgetMonthly;
		this.statusTemplate = statusTemplate;
	}
    
    
	
    
    
    
}
