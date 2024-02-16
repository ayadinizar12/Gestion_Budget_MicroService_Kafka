package com.gestion.budget.communes.template.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestion.budget.communes.manager.entite.Notification;
import com.gestion.budget.communes.template.entite.Template;

public interface TemplateRepo extends JpaRepository<Template, Integer>{

	
}