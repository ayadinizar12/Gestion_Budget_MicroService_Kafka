package com.gestion.budget.communes.grade.composant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gestion.budget.communes.grade.service.Grade_RessourceService;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner{

	@Autowired
	Grade_RessourceService grade_RessourceService;

	@Override
	public void run(String... args) throws Exception {
		grade_RessourceService.findByIdDev(1);
		grade_RessourceService.findByIdDevops(2);
		grade_RessourceService.findByIdQa(3);
		grade_RessourceService.findByIdSup(4);
	
	}
	
	
	
}

