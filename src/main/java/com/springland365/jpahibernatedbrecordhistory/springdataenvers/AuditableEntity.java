package com.springland365.jpahibernatedbrecordhistory.springdataenvers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Audited
@ToString

public class AuditableEntity {

    @Id
    @GeneratedValue
    Long id;

    @Version
    protected Integer version;


    @Column
    protected String desc ;

    @CreatedBy
    @Column(name = "creation_by")
    protected String createdBy;

    @CreatedDate
    @Column(name = "creation_dt")
    protected LocalDateTime creationDt;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy;
    @LastModifiedDate

    @Column(name = "last_modified_dt")
    protected LocalDateTime lastModifiedDt;

}
