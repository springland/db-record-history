package com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Entity
@SQLDelete(
      sql= """
            update BOOK_ENTITY set deleted = true where id = ? and version = ? 
        """
)
@Where( clause =  " deleted = false")
//@EntityListeners(AuditableEntityListener.class)
@EntityListeners(AuditingEntityListener.class)
public class BookEntity extends AuditableEntity {


    @Column
    String name ;

    @Column
    String author ;

    @Column
    String description ;

    @Column
    boolean deleted ;

}
