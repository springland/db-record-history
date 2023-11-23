package com.springland365.jpahibernatedbrecordhistory.optimistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class VersionedDepartmentLoader implements ApplicationRunner {

    @Autowired
    VersionedDepartmentRepo repo ;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        VersionedDepartmentEntity versionedDeptEntity = new VersionedDepartmentEntity();
        versionedDeptEntity.setName("dept1");
        versionedDeptEntity.setDescription(" version 1");

        versionedDeptEntity = repo.save(versionedDeptEntity);

        Thread.sleep(2000);
        versionedDeptEntity.setDescription(" Update to version 2");
        versionedDeptEntity = repo.save(versionedDeptEntity);
        Thread.sleep(2000);

        versionedDeptEntity.setDescription(" Update to version 3");
        versionedDeptEntity = repo.save(versionedDeptEntity);

    }
}
