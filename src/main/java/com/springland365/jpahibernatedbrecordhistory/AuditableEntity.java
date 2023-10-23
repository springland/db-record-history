package com.springland365.jpahibernatedbrecordhistory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class AuditableEntity {

    @Id
    @GeneratedValue
    Long id;

    @Version
    protected Integer  version ;

    @CreatedBy
    protected String createdBy;

    @CreatedDate
    protected LocalDateTime creationDt;

    @LastModifiedBy
    protected String lastModifiedBy ;
    @LastModifiedDate
    protected LocalDateTime  lastModifiedDt;

}
