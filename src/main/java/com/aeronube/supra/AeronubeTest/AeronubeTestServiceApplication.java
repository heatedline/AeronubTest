package com.aeronube.supra.AeronubeTest;

import java.util.Properties;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAutoConfiguration
public class AeronubeTestServiceApplication extends SpringBootServletInitializer {
   
	private EntityManager entityManager;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AeronubeTestServiceApplication.class, args);
	}
	
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Calcutta"));

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AeronubeTestServiceApplication.class);
	}

	@Bean
	@Primary
	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false);

		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.show_sql", "false");
		jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.aeronube.supra.AeronubeTest");
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
		localContainerEntityManagerFactoryBean.afterPropertiesSet();
		this.entityManager = localContainerEntityManagerFactoryBean.getObject().createEntityManager();
		return localContainerEntityManagerFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}

	@Bean
	public Mapper mapper() {
		return new DozerBeanMapper();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println("cors configure");
				registry.addMapping("/**").allowedOrigins("http://localhost");
			}
		};
	}

	@Bean
	@DependsOn("entityManagerFactory")
	public SessionFactory sessionfactory() {
		Session session = entityManager.unwrap(Session.class);
		SessionFactory sessionFactory = session.getSessionFactory();
		return sessionFactory;
	}
	
}
