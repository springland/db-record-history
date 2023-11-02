package com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.AbstractRevisionDTO;
import lombok.Data;

import java.util.List;

@Data
public class AddressRevisionDTO extends AbstractRevisionDTO {

    String street;

    String city ;
    String state ;
    String zipCode ;

    List<PersonRevisionDTO> residents  ;
}
