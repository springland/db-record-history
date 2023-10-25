package com.springland365.jpahibernatedbrecordhistory.springdataenvers;

import com.springland365.jpahibernatedbrecordhistory.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
@EntityListeners(AuditingEntityListener.class)

public class ProjectEntity extends AuditableEntity {

    String name ;
    String description ;

    @ManyToMany(mappedBy = "projects")
    Set<EmployeeEntity> employees = new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hash(this.getId() , this.getVersion());
    }

    public boolean equals(Object o){

        if( o == null){
            return false ;
        }

        if(this == o){
            return true ;
        }

        if(this.getClass() != o.getClass()){
            return false ;
        }

        ProjectEntity another = (ProjectEntity) o ;
        return Objects.equals(this.getId() , another.getId()) && Objects.equals(this.getVersion() , another.getVersion());
    }

}
