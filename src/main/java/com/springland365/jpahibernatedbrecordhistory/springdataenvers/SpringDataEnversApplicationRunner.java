package com.springland365.jpahibernatedbrecordhistory.springdataenvers;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany.EmployeeEntity;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany.EmployeeRepo;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany.ProjectEntity;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany.ProjectRepo;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany.AddressEntity;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany.AddressRepository;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany.PersonEntity;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple.BookEntity;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.history.RevisionMetadata;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SpringDataEnversApplicationRunner implements ApplicationRunner {


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

        ProjectEntity beta = new ProjectEntity();
        beta.setName("Beta") ;

        EmployeeEntity james = new EmployeeEntity();
        james.setFirstName("James");
        james.setLastName("Miller");

        EmployeeEntity john = new EmployeeEntity();
        john.setFirstName(("John"));
        john.setLastName("Smith");

        alpha.getEmployees().add(james);
        james.getProjects().add(alpha);

        alpha.getEmployees().add(john);
        james.getProjects().add(alpha);


        john = employeeRepo.save(john);

        james = employeeRepo.save(james);

        beta = projectRepo.save(beta);

        beta.getEmployees().add(james);
        james.getProjects().add(beta);

        james = employeeRepo.save(james);
    }

}
