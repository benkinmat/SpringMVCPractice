package springmvc.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(value = { "springmvc.configuration" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfig {

	@Autowired
	private Environment environment;

	private Properties getProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect",
				environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql",
				environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql",
				environment.getRequiredProperty("hibernate.format_sql"));

		return properties;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();

		datasource.setUsername(environment.getProperty("jdbc.username"));
		datasource.setPassword(environment.getProperty("jdbc.password"));
		datasource.setUrl(environment.getProperty("jdbc.url"));
		datasource.setDriverClassName(environment
				.getProperty("jdbc.driverClassName"));

		return datasource;

	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFact = new LocalSessionFactoryBean();

		sessionFact.setDataSource(dataSource());
		sessionFact.setPackagesToScan(new String[] {"springmvc.model"});
		sessionFact.setHibernateProperties(getProperties());

		return sessionFact;

	}

	@Bean
	@Autowired
	public HibernateTransactionManager transaction(SessionFactory session) {
		HibernateTransactionManager transaction = new HibernateTransactionManager();

		transaction.setSessionFactory(session);

		return transaction;

	}

}
