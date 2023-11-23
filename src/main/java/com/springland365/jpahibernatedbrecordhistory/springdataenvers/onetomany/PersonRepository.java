package com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity , Long> , RevisionRepository<PersonEntity , Long , Integer> {


    @Query(
            value = "SELECT * from tbl_person" , nativeQuery = true
    )
    List<PersonEntity> selectAllNativeIncludeDeleted();
}
