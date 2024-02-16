package com.gestion.budget.communes.manager.entite;

import com.gestion.budget.communes.event.StatusBudget;
import com.gestion.budget.communes.event.StatusTemplate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "ProjetInfoRequest")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
public class Projet {

		@Id @GeneratedValue
	    private Integer Id;
	    private String client;
	    private String projetName;
	    private double budget;
	    private Integer homme;
	    private Integer jours;
	    private String delivery;
	    @Enumerated(EnumType.STRING)
	    private StatusBudget statusBudget  ;
	    @Enumerated(EnumType.STRING)
	    private StatusTemplate statusTemplate ;
		public Integer getId() {
			return Id;
		}
		public void setId(Integer id) {
			Id = id;
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
		public StatusBudget getStatusBudget() {
			return statusBudget;
		}
		public void setStatusBudget(StatusBudget statusBudget) {
			this.statusBudget = statusBudget;
		}
		public StatusTemplate getStatusTemplate() {
			return statusTemplate;
		}
		public void setStatusTemplate(StatusTemplate statusTemplate) {
			this.statusTemplate = statusTemplate;
		}
		public Projet(String client, String projetName, double budget, Integer homme, Integer jours, String delivery,
				StatusBudget statusBudget, StatusTemplate statusTemplate) {
			super();
			this.client = client;
			this.projetName = projetName;
			this.budget = budget;
			this.homme = homme;
			this.jours = jours;
			this.delivery = delivery;
			this.statusBudget = statusBudget;
			this.statusTemplate = statusTemplate;
		}
		
		public Projet() {
			
		}
		
		
		
}
