package com.springland365.jpahibernatedbrecordhistory.springdataenvers;

import com.springland365.jpahibernatedbrecordhistory.AuditableEntity;
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
@Audited
@Entity
@EntityListeners(AuditingEntityListener.class)
public class BookEntity extends AuditableEntity {


    @Column
    String name ;

    @Column
    String author ;

    @Column
    String description ;



}
