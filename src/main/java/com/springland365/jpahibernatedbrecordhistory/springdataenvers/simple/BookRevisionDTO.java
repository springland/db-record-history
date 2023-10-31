package com.springland365.jpahibernatedbrecordhistory.springdataenvers.simple;

import com.springland365.jpahibernatedbrecordhistory.springdataenvers.AbstractRevisionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookRevisionDTO extends AbstractRevisionDTO {


    String name ;

    String author ;

    String description ;


}
