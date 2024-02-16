package com.gestion.budget.communes.manager.entite;

import com.gestion.budget.communes.event.StatusBudget;
import com.gestion.budget.communes.event.StatusTemplate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
@Table(name = "Ressource_List")
public class GradeRessource {

	@Id
	private Integer projetId;
	private Integer homme;
	private Integer developer;
	private Integer quality;
	private Integer devops;
	private Integer support;
	public Integer getProjetId() {
		return projetId;
	}
	public void setProjetId(Integer projetId) {
		this.projetId = projetId;
	}
	public Integer getHomme() {
		return homme;
	}
	public void setHomme(Integer homme) {
		this.homme = homme;
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
	public GradeRessource(Integer projetId, Integer homme, Integer developer, Integer quality, Integer devops,
			Integer support) {
		
		this.projetId = projetId;
		this.homme = homme;
		this.developer = developer;
		this.quality = quality;
		this.devops = devops;
		this.support = support;
	}
	public GradeRessource() {
		
	}
	
	
	
}
