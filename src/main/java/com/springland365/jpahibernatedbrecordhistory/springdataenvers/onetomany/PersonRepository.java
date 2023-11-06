package com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface PersonRepository extends JpaRepository<PersonEntity , Long> , RevisionRepository<PersonEntity , Long , Integer> {
}
