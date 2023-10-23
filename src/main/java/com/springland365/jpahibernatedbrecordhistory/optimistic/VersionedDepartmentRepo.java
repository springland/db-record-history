package com.springland365.jpahibernatedbrecordhistory.optimistic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VersionedDepartmentRepo extends JpaRepository< VersionedDepartmentEntity , Long> {
}
