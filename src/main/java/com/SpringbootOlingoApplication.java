package com;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

import com.luigi.bean.odata.StudentODataAgent;
import com.mario.bean.odata.AddressODataAgent;
import com.mario.bean.odata.EmployeeODataAgent;
import com.mario.bean.odata.LectureODataAgent;
import com.mario.bean.odata.PersonODataAgent;
import com.sap.dbs.dbx.i068191.annotation.processor.MyODataServiceFactory;
import com.sap.dbs.dbx.i068191.servlet.SimpleODataServlet;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ServletComponentScan
@Slf4j
@ComponentScan(basePackageClasses = { com.sap.dbs.dbx.i068191.util.SpringUtils.class, SpringbootOlingoApplication.class })
public class SpringbootOlingoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootOlingoApplication.class, args);
	}

	@Bean(name = "standalone")
	@DependsOn("h2TcpServer")
	public DataSource h2Standalone() {
		log.info("--- create database source ---");
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("org.h2.Driver");
		// Tcp connection
		//ds.setUrl("jdbc:h2:tcp://localhost:8082/~/test");
		//
		ds.setUrl("jdbc:h2:~/test");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;
	}
	
	@Bean(name = "h2TcpServer", destroyMethod = "stop")
	public Server h2TcpServer() throws SQLException {
		log.info("--- create h2 database service ---");
		// return Server.createTcpServer("-tcpPort", "8082", "-trace").start();
		return Server.createWebServer("-tcpPort", "8082", "-trace").start();
	}
	
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean emf(JpaVendorAdapter adapter, DataSource ds) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setPackagesToScan("com.mario.bean","com.luigi.bean");
		factory.setJpaVendorAdapter(adapter);
		factory.setJtaDataSource(ds);
		return factory;
	}
	
	@Bean 
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean
	public JpaVendorAdapter eclipseLink() {
		EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		return adapter;
	}
	
	@Bean(name="MarioODataServiceFactory")
	public ODataServiceFactory marioServiceFactory(){
		return new MyODataServiceFactory("com.mario.bean");
	}
	
	@Bean(name="LuigiODataServiceFactory")
	public ODataServiceFactory luigiServiceFactory(){
		return new MyODataServiceFactory("com.luigi.bean");
	}
	
	@Bean(name="com.mario.bean.EmployeeODataAgent")
	public EmployeeODataAgent marioEmployeeODataAgent(){
		log.info("return EmployeeODataAgent object");
		return new EmployeeODataAgent();
	}
	
	@Bean(name="com.mario.bean.AddressODataAgent")
	public AddressODataAgent marioAddressODataAgent(){
		log.info("return AddressODataAgent object");
		return new AddressODataAgent();
	}
	
	@Bean(name="com.mario.bean.LectureODataAgent")
	public LectureODataAgent marioLectureODataAgent(){
		log.info("return LectureODataAgent object");
		return new LectureODataAgent();
	}
	
	@Bean(name="com.mario.bean.PersonODataAgent")
	public PersonODataAgent marioPersonODataAgent(){
		log.info("return PersonODataAgent object");
		return new PersonODataAgent();
	}
	
	@Bean(name="com.luigi.bean.StudentODataAgent")
	public StudentODataAgent luigiEmployeeODataAgent(){
		return new StudentODataAgent();
	}
	
}

@WebServlet(urlPatterns = { "/mario_odata.svc/*" })
class MarioODataServlet extends SimpleODataServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		setoDataServiceFactoryBeanName("MarioODataServiceFactory");
	}
}

@WebServlet(urlPatterns = { "/luigi_odata.svc/*" })
class LuigiODataServlet extends SimpleODataServlet {

	private static final long serialVersionUID = 2L;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		setoDataServiceFactoryBeanName("LuigiODataServiceFactory");
	}
}