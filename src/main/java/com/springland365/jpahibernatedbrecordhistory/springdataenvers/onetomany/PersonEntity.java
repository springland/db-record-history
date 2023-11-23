package com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Entity
@Table(name="tbl_person")
@Data
@Audited
@AuditTable(value = "tbl_person_audit")
@NoArgsConstructor
@AllArgsConstructor

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
        return Objects.hash(this.getId() , this.version);
    }

    @Override
    public boolean equals(Object o){
        if( o == null){
            return false ;
        }

        if( this == o){
            return true ;
        }

        if(this.getClass() != o.getClass()){
            return false ;
        }

        PersonEntity  another = (PersonEntity) o ;
        return Objects.equals(this.getId() , another.getId()) && Objects.equals(this.getVersion() , another.getVersion());
    }

}
