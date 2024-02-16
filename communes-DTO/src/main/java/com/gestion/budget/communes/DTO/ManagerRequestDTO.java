package com.gestion.budget.communes.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ManagerRequestDTO {

	private String client;
    private String projetName;
    private  double budget ;
    private Integer homme;
    private Integer jours;
    private Integer developer;
    private Integer quality;
    private  Integer devops;
    private Integer support;
    private String delivery;
    private Integer projetId;
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
	public Integer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Integer developer) {
		this.developer = developer;
	}
	public Integer getQuality() {
		return quality;
	}
	public void setQuality(Integer quality) {
		this.quality = quality;
	}
	public Integer getDevops() {
		return devops;
	}
	public void setDevops(Integer devops) {
		this.devops = devops;
	}
	public Integer getSupport() {
		return support;
	}
	public void setSupport(Integer support) {
		this.support = support;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public Integer getProjetId() {
		return projetId;
	}
	public void setProjetId(Integer projetId) {
		this.projetId = projetId;
	}
	public ManagerRequestDTO(String client, String projetName, double budget, Integer homme, Integer jours,
			Integer developer, Integer quality, Integer devops, Integer support, String delivery, Integer projetId) {
		
		this.client = client;
		this.projetName = projetName;
		this.budget = budget;
		this.homme = homme;
		this.jours = jours;
		this.developer = developer;
		this.quality = quality;
		this.devops = devops;
		this.support = support;
		this.delivery = delivery;
		this.projetId = projetId;
	}
	
	public ManagerRequestDTO() {
		
		
	}
	
    
    
    
}
