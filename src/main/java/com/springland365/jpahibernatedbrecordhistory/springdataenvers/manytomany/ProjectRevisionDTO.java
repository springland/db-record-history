package com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.AbstractRevisionDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProjectRevisionDTO extends AbstractRevisionDTO {

    String name ;

    String description ;

    List<EmployeeRevisionDTO>  team ;
}
