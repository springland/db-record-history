# db-record-history

Per https://www.baeldung.com/spring-data-jpa-generate-db-schema  the below can generaate DDL 

start application , a new file create.sql is created under in root


spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create
spring.jpa.properties.javax.persistence.schema-generation.scripts.create-source=metadata
spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=create.sql

copy create.sql to changelog to deploy

However it does not work well. The app needs to start. Then it will fail before the schema is deployed

And spring.jpa.properties.javax.persistence.schema-generation.scripts.action only supports create/delete , it does not support alter