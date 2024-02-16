package com.gestion.budget.communes.grade.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class SumSalairesProjet {
	
	@Id
    private Integer projetId;
    private Double sumSalaire;
    private Long duration;
}
