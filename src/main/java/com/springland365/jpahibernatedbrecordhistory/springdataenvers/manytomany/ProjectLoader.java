package com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ProjectLoader implements ApplicationRunner {
    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    EmployeeRepo employeeRepo ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createEmployeeProject();
    }

    protected void createEmployeeProject(){
        ProjectEntity alpha = new ProjectEntity();
        alpha.setName("Alpha");
        alpha.setDescription(" Project alpha  with team member");
        ProjectEntity beta = new ProjectEntity();
        beta.setName("Beta") ;
        beta.setDescription("Project beta inital , no team member");

        EmployeeEntity james = new EmployeeEntity();
        james.setFirstName("James");
        james.setLastName("Miller");

        EmployeeEntity john = new EmployeeEntity();
        john.setFirstName(("John"));
        john.setLastName("Smith");

        alpha.getEmployees().add(james);
        james.getProjects().add(alpha);

        alpha.getEmployees().add(john);
        john.getProjects().add(alpha);

        projectRepo.save(alpha);


        beta = projectRepo.save(beta);

        beta.setDescription(" Add James  to beta as well , james in both Alpha and Beta ");
        beta.getEmployees().add(james);
        james.getProjects().add(beta);

        projectRepo.save(beta);
    }

}
