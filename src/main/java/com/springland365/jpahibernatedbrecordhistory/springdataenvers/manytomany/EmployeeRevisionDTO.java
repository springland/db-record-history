package com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.AbstractRevisionDTO;
import lombok.Data;

@Data
public class EmployeeRevisionDTO  extends AbstractRevisionDTO {
    String firstName ;

    String lastName ;

}
