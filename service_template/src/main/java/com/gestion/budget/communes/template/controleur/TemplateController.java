package com.gestion.budget.communes.template.controleur;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.budget.communes.template.entite.Template;
import com.gestion.budget.communes.template.service.ServiceTemplate;

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private ServiceTemplate templateService;

    @GetMapping("/{id}")
    public Optional<Template> getTemplateById(@PathVariable int id ){
        return templateService.getTemplateById(id);
    }
    @GetMapping
    public List<Template> getTemplate(){return templateService.getAllTemplate();}
}
