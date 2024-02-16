package com.gestion.budget.communes.entite;

import com.gestion.budget.communes.event.StatusEmploye;

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
public class Employe {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEmployee;
    private Integer projetId;
    private String fullName;
    private String grade;
    @Enumerated(EnumType.STRING)
    private StatusEmploye status;
	public Integer getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}
	public Integer getProjetId() {
		return projetId;
	}
	public void setProjetId(Integer projetId) {
		this.projetId = projetId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public StatusEmploye getStatus() {
		return status;
	}
	public void setStatus(StatusEmploye status) {
		this.status = status;
	}
    
    
}
