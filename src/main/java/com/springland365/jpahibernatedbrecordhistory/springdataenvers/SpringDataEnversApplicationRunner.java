package com.springland365.jpahibernatedbrecordhistory.springdataenvers;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringDataEnversApplicationRunner implements ApplicationRunner {
    @Autowired
    BookRepository  bookRepository ;
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

        Thread.sleep(2000);
        bookEntity.setDescription("Version 2");
        bookEntity = bookRepository.save(bookEntity);

    }
}
