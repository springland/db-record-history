package com.springland365.jpahibernatedbrecordhistory.springdataenvers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.history.RevisionMetadata.RevisionType;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractRevisionDTO {
    protected Integer revisionNumber  ;

    protected RevisionType revisionType ;

    protected Long id;

    protected Integer version;

    protected String createdBy;

    protected String desc ;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime creationDt;

    protected String lastModifiedBy;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime lastModifiedDt;

}
