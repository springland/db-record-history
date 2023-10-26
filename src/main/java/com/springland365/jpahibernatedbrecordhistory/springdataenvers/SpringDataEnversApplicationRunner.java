package com.springland365.jpahibernatedbrecordhistory.springdataenvers;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany.EmployeeEntity;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany.EmployeeRepo;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany.ProjectEntity;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany.ProjectRepo;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany.AddressEntity;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany.AddressRepository;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany.PersonEntity;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.single.BookEntity;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.single.BookRepository;
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
    BookRepository bookRepository ;

    @Autowired
    AddressRepository addressRepository ;

    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    EmployeeRepo employeeRepo ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createBooks();
        createPersonAddress();
        createEmployeeProject();
    }

    protected void createBooks()  throws  Exception{
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Effective Java");
        bookEntity.setAuthor("Joshua Bloch");
        bookEntity.setDescription("Version 1");
        bookEntity = bookRepository.save(bookEntity);

        Thread.sleep(2000);
        bookEntity.setDescription("Version 2");
        bookEntity = bookRepository.save(bookEntity);

        bookRepository.flush();

       // printBookHistory(bookEntity.getId());
    }

    protected void printBookHistory(Long id) {
        Revisions<Integer, BookEntity> revisions = bookRepository.findRevisions(id);

        revisions.stream().forEach(
                rev -> {
                    BookEntity  bookEntity = rev.getEntity();
                    log.info(bookEntity.toString());

                }

        );

    }
    protected void createPersonAddress() throws Exception {
        AddressEntity address = new AddressEntity();
        address.setCity("New York");
        address.setState("NY");
        address.setZipCode("10172");
        address.setStreet("277 Park Ave");

        PersonEntity john = new PersonEntity("John" , "Doe" , address);

        List<PersonEntity>  residences = List.of(
                john
        );
        address.setResidents(residences);
        address = addressRepository.save(address);

        residences = new ArrayList<>();
        PersonEntity jane = new PersonEntity("Jane" , "Doe" , address);
        residences.addAll(address.getResidents());
        residences.add(jane);
        address.setResidents(residences);
        address = addressRepository.save(address);

        addressRepository.flush();


       // printAddressHistory(address.getId());
    }

    protected void printAddressHistory(Long id){
        Revisions<  Integer , AddressEntity> revisions= addressRepository.findRevisions(id);
        revisions.stream().forEach(
                rev -> printAddressRevision(rev.getMetadata() , rev.getEntity())
        );

    }

    protected void printAddressRevision(RevisionMetadata<Integer> revisionMetadata , AddressEntity address){
        log.info(revisionMetadata.toString());
        log.info(address.toString());


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
