package br.com.ey.msheroi.config.datasource.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = SqlserverDefinitions.REPO_PACKAGES,
		entityManagerFactoryRef = SqlserverDefinitions.BEAN_NAME_ENTITY_MANAGER_FACTORY,
		transactionManagerRef= SqlserverDefinitions.BEAN_NAME_TRANSACTION_MANAGER
)
public class SqlserverDatasource extends DatasourceHikariConnection {

	@Autowired
	private Environment environment;

	public SqlserverDatasource() {
		super();
	}

	@Bean(name = SqlserverDefinitions.BEAN_NAME_DATASOURCE)
	@Primary
	public DataSource datasourceSqlserver() {
		log.info("... SQLSERVER Datasource started ...");
		HikariConfig hikariConfig = super.getConnectionDatasource(environment, SqlserverDefinitions.CONTEXT_BASE);
		return new HikariDataSource(hikariConfig);
	}


	@Bean(name = SqlserverDefinitions.BEAN_NAME_ENTITY_MANAGER_FACTORY)
	@Primary
	public LocalContainerEntityManagerFactoryBean sqlServerEntityManagerFactory(EntityManagerFactoryBuilder builder) {

		log.info("... SQLSERVER Entity Manager Factory started ...");
		return builder
				.dataSource(datasourceSqlserver())
				.packages(SqlserverDefinitions.ENTITY_PACKAGES)
				.properties(super.getProperties(environment, SqlserverDefinitions.CONTEXT_BASE))
				.build();
	}


	@Bean(name = SqlserverDefinitions.BEAN_NAME_TRANSACTION_MANAGER)
	@Primary
	public PlatformTransactionManager sqlServerTransactionManager(
										final @Qualifier(SqlserverDefinitions.BEAN_NAME_ENTITY_MANAGER_FACTORY)
														LocalContainerEntityManagerFactoryBean memberEntityManagerFactory) {
		log.info("... SQLSERVER Transaction Manager started ...");
		return new JpaTransactionManager(memberEntityManagerFactory.getObject());
	}


}
