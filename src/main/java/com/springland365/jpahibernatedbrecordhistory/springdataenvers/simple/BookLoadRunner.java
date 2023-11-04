package com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class BookLoadRunner implements ApplicationRunner {

    @Autowired
    BookRepository bookRepository ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createBooks();
    }

    protected void createBooks()  throws  Exception{
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName("Effective Java");
        bookEntity.setAuthor("Joshua Bloch");
        bookEntity.setDescription("Version 1");
        bookEntity = bookRepository.save(bookEntity);

        Thread.sleep(1000);
        bookEntity.setDescription("Version 2");
        bookEntity = bookRepository.save(bookEntity);

        bookRepository.flush();



        bookEntity = new BookEntity();
        bookEntity.setName("Design Patterns: Elements of Reusable Object-Oriented Software");
        bookEntity.setAuthor("Erich Gamma");
        bookEntity.setDescription("V1");
        Thread.sleep(1000);

        bookEntity = bookRepository.save(bookEntity);
        bookEntity.setAuthor("Erich Gamma, Richard Helm");
        bookEntity.setDescription("V2");

        Thread.sleep(1000);
        bookEntity = bookRepository.save(bookEntity);

        bookEntity.setAuthor("Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides");
        bookEntity.setDescription("V3");
        bookEntity = bookRepository.save(bookEntity);

        Thread.sleep(1000);
        bookRepository.delete(bookEntity);
    }

}
