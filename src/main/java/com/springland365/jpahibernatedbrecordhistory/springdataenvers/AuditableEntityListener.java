package com.springland365.jpahibernatedbrecordhistory.springdataenvers;


import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditableEntityListener   {

    @PrePersist
    public void beforePersist(Object entity) {
        if (entity instanceof AuditableEntity) {
            AuditableEntity auditableEntity = (AuditableEntity) entity;
            // Set audit information before persisting the entity
            auditableEntity.setCreationDt(LocalDateTime.now());
            auditableEntity.setCreatedBy("system"); // Set the creator username or ID
            auditableEntity.setLastModifiedDt(LocalDateTime.now());
            auditableEntity.setLastModifiedBy("system"); // Set the creator username or ID

        }
    }
    @PreUpdate
    public void beforeUpdate(Object entity){

        if (entity instanceof AuditableEntity) {
            AuditableEntity auditableEntity = (AuditableEntity) entity;
            // Set audit information before persisting the entity
            auditableEntity.setLastModifiedDt(LocalDateTime.now());
            auditableEntity.setLastModifiedBy("system"); // Set the creator username or ID
        }

    }



}
