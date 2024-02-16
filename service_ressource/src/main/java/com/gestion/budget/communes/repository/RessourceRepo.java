package com.gestion.budget.communes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestion.budget.communes.entite.Notification;
import com.gestion.budget.communes.entite.Ressource;

public interface RessourceRepo extends JpaRepository<Ressource, Integer>{

}