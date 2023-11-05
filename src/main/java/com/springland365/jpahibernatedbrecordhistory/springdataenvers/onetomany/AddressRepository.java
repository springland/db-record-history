package com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity , Long> , RevisionRepository<AddressEntity , Long , Integer> {

    @Query(
            value = "SELECT * from ADDRESS_ENTITY" , nativeQuery = true
    )
    public List<AddressEntity> selectAllAddressNativeIncludeDeleted() ;
}
