package com.springland365.jpahibernatedbrecordhistory.optimistic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class VersionedDepartmentEntity {

    @Id
    @GeneratedValue

    Long id ;

    @Column
    String name ;

    @Column
    String description ;

    @Version
    Integer version ;

    @CreatedBy
    String createdBy;

    @CreatedDate
    protected LocalDateTime creationDt;

    @LastModifiedBy
    String lastModifiedBy ;
    @LastModifiedDate
    protected LocalDateTime  lastModifiedDt;
}
