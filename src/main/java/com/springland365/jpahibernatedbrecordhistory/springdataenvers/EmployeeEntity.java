package com.springland365.jpahibernatedbrecordhistory.springdataenvers;

import com.springland365.jpahibernatedbrecordhistory.AuditableEntity;
import jakarta.persistence.*;
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

public class EmployeeEntity extends AuditableEntity {
    String firstName ;

    String lastName ;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Employee_Project",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    Set<ProjectEntity>  projects = new HashSet<>();


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

        EmployeeEntity another = (EmployeeEntity) o ;
        return Objects.equals(this.getId() , another.getId()) && Objects.equals(this.getVersion() , another.getVersion());
    }
}
