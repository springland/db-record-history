package com.springland365.jpahibernatedbrecordhistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaHibernateDbRecordHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateDbRecordHistoryApplication.class, args);
	}

}
