package com.gestion.budget.communes.grade.entite;

import com.gestion.budget.communes.event.StatusGradeCost;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class GradeSalairesProjet {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer projetId;
	private String garde;
	private Double salaire;
	private Integer nombre;
	private Double somme;
	@Enumerated(EnumType.STRING)
	private StatusGradeCost statusGradeCost;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProjetId() {
		return projetId;
	}
	public void setProjetId(Integer projetId) {
		this.projetId = projetId;
	}
	public String getGarde() {
		return garde;
	}
	public void setGarde(String garde) {
		this.garde = garde;
	}
	public Double getSalaire() {
		return salaire;
	}
	public void setSalaire(Double salaire) {
		this.salaire = salaire;
	}
	public Integer getNombre() {
		return nombre;
	}
	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}
	public Double getSomme() {
		return somme;
	}
	public void setSomme(Double somme) {
		this.somme = somme;
	}
	public StatusGradeCost getStatusGradeCost() {
		return statusGradeCost;
	}
	public void setStatusGradeCost(StatusGradeCost statusGradeCost) {
		this.statusGradeCost = statusGradeCost;
	}
	public GradeSalairesProjet(Integer projetId, String garde, Double salaire, Integer nombre, Double somme,
			StatusGradeCost statusGradeCost) {
	
		this.projetId = projetId;
		this.garde = garde;
		this.salaire = salaire;
		this.nombre = nombre;
		this.somme = somme;
		this.statusGradeCost = statusGradeCost;
	} 


	
	
}
