package com.springland365.jpahibernatedbrecordhistory.springdataenvers;

import com.springland365.jpahibernatedbrecordhistory.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Audited
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class PersonEntity extends AuditableEntity {

    String firstName ;

    String lastName ;


    @ManyToOne
    AddressEntity address ;

    public String toString(){

        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append("firstName:" );
        builder.append(this.firstName);
        builder.append(" lastName: ");
        builder.append(this.lastName);
        return builder.toString();
    }
    @Override
    public int hashCode(){
        return  this.getId().intValue() ;
    }

}
