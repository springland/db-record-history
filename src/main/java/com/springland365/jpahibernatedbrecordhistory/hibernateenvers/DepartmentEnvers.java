package com.springland365.jpahibernatedbrecordhistory.hibernateenvers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class DepartmentEnvers {
    @Id
    Long id ;

    @Column
    String name ;

    @Column
    String description ;

    @CreatedBy
    @Column
    private String createdBy;

    @CreatedDate
    @Column
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column
    private String lastModifiedBy;

    @LastModifiedDate
    @Column
    private LocalDateTime lastModifiedDate;
}
