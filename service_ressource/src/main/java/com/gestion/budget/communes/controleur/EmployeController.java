package com.gestion.budget.communes.controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.budget.communes.DTO.EmployeRequestDTO;
import com.gestion.budget.communes.entite.Employe;
import com.gestion.budget.communes.entite.Notification;
import com.gestion.budget.communes.repository.NotificationRepo;
import com.gestion.budget.communes.service.ServiceEmploye;

@RestController
@RequestMapping("/employe")
public class EmployeController {

    @Autowired
    private ServiceEmploye serviceEmploye ;
    @PostMapping("/create")
    public Employe addEmploye(EmployeRequestDTO employeRequestDTO){
        return serviceEmploye.AddEmploye(employeRequestDTO);
    }
}