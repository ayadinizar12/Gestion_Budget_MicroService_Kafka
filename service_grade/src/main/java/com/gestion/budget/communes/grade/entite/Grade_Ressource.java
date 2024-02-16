package com.gestion.budget.communes.grade.entite;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Grade_Ressource {

	@Id @GeneratedValue
	private Integer gradeId;
	private String grade;
	private Double salaire;
	private LocalDate dateUpDate=LocalDate.now();
	
	
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
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
	public LocalDate getDateUpDate() {
		return dateUpDate;
	}
	public void setDateUpDate(LocalDate dateUpDate) {
		this.dateUpDate = dateUpDate;
	}
	

}
