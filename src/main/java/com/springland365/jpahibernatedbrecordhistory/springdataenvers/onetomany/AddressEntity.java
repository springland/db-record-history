package com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.AuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
@EntityListeners(AuditingEntityListener.class)

public class AddressEntity extends AuditableEntity {

    String street;

    String city ;
    String state ;
    String zipCode ;

    @OneToMany(mappedBy = "address"  ,cascade= CascadeType.ALL)
    List<PersonEntity> residents ;


    public int hashCode()
    {
        return Objects.hash(this.getId() , this.getVersion());
    }

    @Override
    public boolean equals(Object o){

        if(o == null){
            return false ;
        }

        if(this == o){
            return true ;
        }

        if(this.getClass() != o.getClass()){
            return false ;
        }

        AddressEntity another = (AddressEntity) o ;
        return Objects.equals(this.getId() , another.getId()) && Objects.equals(this.getVersion() , another.getVersion());
    }



}
