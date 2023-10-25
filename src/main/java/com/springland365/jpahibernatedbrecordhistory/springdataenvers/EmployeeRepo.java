package com.springland365.jpahibernatedbrecordhistory.springdataenvers;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity , Long>, RevisionRepository<EmployeeEntity , Long , Integer> {
}
