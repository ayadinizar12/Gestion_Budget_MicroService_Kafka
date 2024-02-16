package com.gestion.budget.communes.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RessourceRequestDTO {

	private Integer projetId;
    private LocalDate startDate ;
    private String endDate;
    private String gradeRessource;
    private Integer employeeNumber;
	
    
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
	public Integer getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public RessourceRequestDTO(Integer projetId, LocalDate startDate, String endDate, String gradeRessource,
			Integer employeeNumber) {
		super();
		this.projetId = projetId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.gradeRessource = gradeRessource;
		this.employeeNumber = employeeNumber;
	}
    
    

    
}
