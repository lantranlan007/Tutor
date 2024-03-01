package com.example.demo;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import liquibase.integration.spring.SpringLiquibase;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private DataSource dataSource;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
//	@Lazy(false)
//	@Bean
//	public SpringLiquibase liquibase() {
//	    SpringLiquibase liquibase = new SpringLiquibase();
//	    liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
//	    liquibase.setDataSource(dataSource);
//	    //liquibase.afterPropertiesSet()
//	    liquibase.setDropFirst(false);
//	    liquibase.setDefaultSchema(null);
//	    liquibase.setLiquibaseSchema("order");
//	  //  liquibase.setDatabaseChangeLogLockTable("ll");
//	    return liquibase;
//	}
	
//	@Bean
//	public SpringLiquibase liquibase(@Qualifier("taskExecutor") TaskExecutor taskExecutor,
//	        DataSource dataSource, LiquibaseProperties liquibaseProperties) {
//
//	    // Use liquibase.integration.spring.SpringLiquibase if you don't want Liquibase to start asynchronously
//	    SpringLiquibase liquibase = new AsyncSpringLiquibase(taskExecutor, env);
//	    liquibase.setDataSource(dataSource);
//	    liquibase.setChangeLog("classpath:config/liquibase/master.xml");
//	    liquibase.setContexts(liquibaseProperties.getContexts());
//	    liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
//	    liquibase.setDropFirst(liquibaseProperties.isDropFirst());
//	    if (env.acceptsProfiles(JHipsterConstants.SPRING_PROFILE_NO_LIQUIBASE)) {
//	        liquibase.setShouldRun(false);
//	    } else {
//	        liquibase.setShouldRun(liquibaseProperties.isEnabled());
//	        log.debug("Configuring Liquibase");
//	    }
//	    return liquibase;
//	}

}
