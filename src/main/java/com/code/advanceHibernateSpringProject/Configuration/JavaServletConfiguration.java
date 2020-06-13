package com.code.advanceHibernateSpringProject.Configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.code.advanceHibernateSpringProject")
@PropertySource("classpath:properties-file.properties")
public class JavaServletConfiguration {

	// Adding environment variable to hold properties
	@Autowired
	private Environment env;

	// Adding logger for for debugging
	Logger logger = Logger.getLogger(getClass().getName());

	// Adding convenient method to convert String to int
	public int getIntMethod(String s) {
		String envString = env.getProperty(s);
		int result = Integer.parseInt(envString);
		return result;
	}

	// Adding view resolver
	@Bean
	public ViewResolver resolver() {

		// Creating instance of class view resolver class
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		// Setting properties
		viewResolver.setPrefix(env.getProperty("prefix"));
		viewResolver.setSuffix(env.getProperty("suffix"));

		// Returning ViewResolver object
		return viewResolver;
	}

	// Adding dataSource
	@Bean
	public DataSource dataSource() {

		// Creating instance of class data source
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		// Setting properties database connection properties
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));

		// Printing connection properties to console for testing
		logger.info("===user" + env.getProperty("jdbc.user"));
		logger.info("===url" + env.getProperty("jdbc.url"));
		logger.info("===password" + env.getProperty("jdbc.password"));

		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		// Adding connection pool properties
		dataSource.setInitialPoolSize(getIntMethod("connection.pool.initialPoolSize"));
		dataSource.setMinPoolSize(getIntMethod("connection.pool.minPoolSize"));
		dataSource.setMaxPoolSize(getIntMethod("connection.pool.maxPoolSize"));
		dataSource.setMaxIdleTime(getIntMethod("connection.pool.maxIdleTime"));

		// Returning DataSource object
		return dataSource;
	}

	// Setting hibernate properties
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties hibernateProperties() {
		return new Properties() {
			{
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
				setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

			}
		};
	}

	// Creating session factory
	@Bean
	public LocalSessionFactoryBean factory() {

		// Creating instance of session factory class
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();

		// Adding entity scan package
		factory.setPackagesToScan("com.code.advanceHibernateSpringProject.Entity");

		// Injecting data source
		factory.setDataSource(dataSource());

		// Injecting hibernate properties
		factory.setHibernateProperties(hibernateProperties());

		// Return session factory object
		return factory;
	}

	// Creating transaction manager
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory factory) {

		// Creating instance of transaction manager class
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();

		// Injecting session factory
		transactionManager.setSessionFactory(factory);

		// Return transaction manager object
		return transactionManager;
	}

}
