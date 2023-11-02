package com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<BookEntity , Long> , RevisionRepository<BookEntity , Long , Integer> {
}
