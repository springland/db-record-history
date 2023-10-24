package com.springland365.jpahibernatedbrecordhistory.springdataenvers;

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
    BookRepository  bookRepository ;

    @Autowired
    AddressRepository addressRepository ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createBooks();
        createPersonAddress();
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

        printBookHistory(bookEntity.getId());
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
        AddressEntity  address = new AddressEntity();
        address.setCity("New York");
        address.setState("NY");
        address.setZipCode("10172");
        address.setStreet("277 Park Ave");

        PersonEntity  john = new PersonEntity("John" , "Doe" , address);

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


        printAddressHistory(address.getId());
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

}
