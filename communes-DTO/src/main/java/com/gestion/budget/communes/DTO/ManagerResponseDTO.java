package com.gestion.budget.communes.DTO;

import com.gestion.budget.communes.event.StatusBudget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ManagerResponseDTO {

	
	private Integer projetId;
    private Integer managerId;
    private double budget;
    private StatusBudget statusBudget ;
	public Integer getProjetId() {
		return projetId;
	}
	public void setProjetId(Integer projetId) {
		this.projetId = projetId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public StatusBudget getStatusBudget() {
		return statusBudget;
	}
	public void setStatusBudget(StatusBudget statusBudget) {
		this.statusBudget = statusBudget;
	}
	
    
 

    
    
    
}
