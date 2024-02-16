package com.gestion.budget.communes.entite;

import java.time.LocalDate;

import com.gestion.budget.communes.event.StatusRessource;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Ressource {

	@Id @GeneratedValue( strategy = GenerationType.AUTO)
	private Integer ressourceId;
    private Integer projetId;
    private LocalDate startDate;
    private String endDate;
    private String gradeRessource;
    private Integer employeNumber;
    @Enumerated(EnumType.STRING)
    private StatusRessource statusRessource ;
	
    public Integer getRessourceId() {
		return ressourceId;
	}

	public void setRessourceId(Integer ressourceId) {
		this.ressourceId = ressourceId;
	}

	public Integer getProjetId() {
		return projetId;
	}

	public void setProjetId(Integer projetId) {
		this.projetId = projetId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getGradeRessource() {
		return gradeRessource;
	}

	public void setGradeRessource(String gradeRessource) {
		this.gradeRessource = gradeRessource;
	}

	public Integer getEmployeNumber() {
		return employeNumber;
	}

	public void setEmployeNumber(Integer employeNumber) {
		this.employeNumber = employeNumber;
	}

	public StatusRessource getStatusRessource() {
		return statusRessource;
	}

	public void setStatusRessource(StatusRessource statusRessource) {
		this.statusRessource = statusRessource;
	}

	public Ressource(Integer projetId, LocalDate startDate, String endDate, String gradeRessource,
			Integer employeNumber, StatusRessource statusRessource ) {
		super();
		this.projetId = projetId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.gradeRessource = gradeRessource;
		this.employeNumber = employeNumber;
		this.statusRessource= statusRessource;
	}

	public Ressource( ) {
		
	}


    

}
