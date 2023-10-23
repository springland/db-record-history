package com.springland365.jpahibernatedbrecordhistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class JpaHibernateDbRecordHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateDbRecordHistoryApplication.class, args);
	}

}
