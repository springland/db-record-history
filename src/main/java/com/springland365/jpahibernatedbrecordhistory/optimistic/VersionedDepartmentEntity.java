package com.springland365.jpahibernatedbrecordhistory.optimistic;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tbl_versioned_department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class VersionedDepartmentEntity extends AuditableEntity {



    @Column
    String name ;

    @Column
    String description ;

}
