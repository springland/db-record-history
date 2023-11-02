package com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectEmployeeController {


    @Autowired
    EmployeeRepo  employeeRepo ;


    @GetMapping("/web/springdataenvers/employee-project")
    public String employeeProject(Model model){


        return "employee-project";
    }



}
