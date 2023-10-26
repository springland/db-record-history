package com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface ProjectRepo extends JpaRepository<ProjectEntity , Long> , RevisionRepository<ProjectEntity , Long , Integer> {
}
