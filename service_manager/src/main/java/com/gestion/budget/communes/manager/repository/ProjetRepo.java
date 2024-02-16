package com.gestion.budget.communes.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.budget.communes.manager.entite.Projet;

public interface ProjetRepo extends JpaRepository<Projet, Integer>{

}
