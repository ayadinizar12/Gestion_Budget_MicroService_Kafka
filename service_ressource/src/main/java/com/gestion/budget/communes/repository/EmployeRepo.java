package com.gestion.budget.communes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestion.budget.communes.entite.Employe;
import com.gestion.budget.communes.entite.Notification;

public interface EmployeRepo extends JpaRepository<Employe, Integer>{

	 @Query("select e from Employe e where e.grade = :grade")
	 List<Employe> findEmployeeByGrade(@Param("grade") String grade);

}