package com.virtusa.marketdataapi.configurations;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.Data;

@Configuration
@Data
@EnableConfigurationProperties(VaultConfiguration.class)
public class DbConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(DbConfiguration.class);

	@Value("${db_url}")
	private String url;
	@Value("${db_driver}")
	private String driverClassName;
	
	private final VaultConfiguration vaultConfiguration;
	
	public DbConfiguration(VaultConfiguration configuration) {
		this.vaultConfiguration = configuration;
	}
	
	@Profile("dev")
	@Bean
	public DataSource devDataSource() {
		DataSourceBuilder builder= DataSourceBuilder.create();
		builder.url(url);
		builder.username(vaultConfiguration.getUsername());
		builder.password(vaultConfiguration.getPassword());
		builder.driverClassName(driverClassName);
		logger.info("Development!");
	    logger.info("----------------------------------------");
	    logger.info("Configuration properties");
	    logger.info("   mysql.username is {}", vaultConfiguration.getUsername());
	    logger.info("   mysql.password is {}", vaultConfiguration.getPassword());
	    logger.info("----------------------------------------");
		return builder.build();
	}
	
	@Profile("qa")
	@Bean
	public DataSource qaDataSource() {
		DataSourceBuilder builder= DataSourceBuilder.create();
		builder.url(url);
		builder.username(vaultConfiguration.getUsername());
		builder.password(vaultConfiguration.getPassword());
		builder.driverClassName(driverClassName);
		logger.info("QA!");
	    logger.info("----------------------------------------");
	    logger.info("Configuration properties");
	    logger.info("   mysql.username is {}", vaultConfiguration.getUsername());
	    logger.info("   mysql.password is {}", vaultConfiguration.getPassword());
	    logger.info("----------------------------------------");
		return builder.build();
	}
	
	@Profile("prod")
	@Bean
	public DataSource prodDataSource() {
		DataSourceBuilder builder= DataSourceBuilder.create();
		builder.url(url);
		builder.username(vaultConfiguration.getUsername());
		builder.password(vaultConfiguration.getPassword());
		builder.driverClassName(driverClassName);
		logger.info("Production!");
	    logger.info("----------------------------------------");
	    logger.info("Configuration properties");
	    logger.info("   mysql.username is {}", vaultConfiguration.getUsername());
	    logger.info("   mysql.password is {}", vaultConfiguration.getPassword());
	    logger.info("----------------------------------------");
		return builder.build();
	}

}
