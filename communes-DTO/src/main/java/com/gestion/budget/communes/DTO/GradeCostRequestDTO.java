package com.gestion.budget.communes.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeCostRequestDTO {

    private Integer projetId ;
    private String grade ;
    private Double salaire;
    private Integer nombre;
	
    public Integer getProjectId() {
		return projetId;
	}
	public void setProjectId(Integer projetId) {
		this.projetId = projetId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
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
	public GradeCostRequestDTO(Integer projetId, String grade, Double salaire, Integer nombre) {
		super();
		this.projetId = projetId;
		this.grade = grade;
		this.salaire = salaire;
		this.nombre = nombre;
	}
    
	
    
}
