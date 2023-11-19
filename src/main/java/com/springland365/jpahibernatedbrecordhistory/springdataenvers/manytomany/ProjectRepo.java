package com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;

public interface ProjectRepo extends JpaRepository<ProjectEntity , Long> , RevisionRepository<ProjectEntity , Long , Integer> {

    @Query(
            value = "SELECT * from PROJECT_ENTITY" , nativeQuery = true
            )
    List<ProjectEntity> selectAllNativeIncludeDeleted();
}
