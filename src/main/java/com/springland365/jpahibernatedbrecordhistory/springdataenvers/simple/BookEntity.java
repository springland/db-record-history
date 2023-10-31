package com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple;

import com.springland365.jpahibernatedbrecordhistory.AuditableEntity;
import com.springland365.jpahibernatedbrecordhistory.springdataenvers.AuditableEntityListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Entity
@EntityListeners(AuditableEntityListener.class)
//@EntityListeners(AuditingEntityListener.class)
public class BookEntity extends AuditableEntity {


    @Column
    String name ;

    @Column
    String author ;

    @Column
    String description ;



}
