package com.springland365.jpahibernatedbrecordhistory.springdataenvers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractRevisionDTO {
    Integer revisionNumber  ;

    Long id;

    protected Integer version;

    protected String createdBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime creationDt;

    protected String lastModifiedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime lastModifiedDt;

}
