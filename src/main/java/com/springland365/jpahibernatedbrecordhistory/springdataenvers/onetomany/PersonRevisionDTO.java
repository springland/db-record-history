package com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.AbstractRevisionDTO;
import lombok.Data;

@Data
public class PersonRevisionDTO extends AbstractRevisionDTO {

    String firstName ;

    String lastName ;

    AddressRevisionDTO  address ;

}
