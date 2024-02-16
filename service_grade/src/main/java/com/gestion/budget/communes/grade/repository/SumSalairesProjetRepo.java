package com.gestion.budget.communes.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.budget.communes.grade.entite.SumSalairesProjet;

public interface SumSalairesProjetRepo extends JpaRepository<SumSalairesProjet, Integer> {
	
}
