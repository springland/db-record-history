package com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity , Long> , RevisionRepository<BookEntity , Long , Integer> {

//    @Modifying
//    @Query("SELECT b from BookEntity b where b.deleted = true or b.deleted = false")

    @Query(value = "SELECT * from BOOK_ENTITY ", nativeQuery = true)
    List<BookEntity> selectAllBooksNativeIncludeDeleted() ;
}
