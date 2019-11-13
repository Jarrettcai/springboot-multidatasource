package com.enn.ygego.sunny.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages =MasterDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "")
public class MasterDataSourceConfig {
 static  final String PACKAGE="com.enn.ygego.sunny.dao.master";
 static final String MAPPER_LOCATION="classpath:mapper/master/*.xml";
 @Value("${master.datasource.url}")
 private String url;

 @Value("${master.datasource.username}")
 private String username;

 @Value("${master.datasource.password}")
 private String password;

 @Value("${master.datasource.driverClassName}")
 private String driverClassName;

 @Bean(name = "masterDataSource")
 @Primary
 public DataSource masterDataSource(){
  DruidDataSource dataSource=new DruidDataSource();
  dataSource.setUrl(url);
  dataSource.setUsername(username);
  dataSource.setPassword(password);
  dataSource.setDriverClassName(driverClassName);
  return dataSource;
 }
 @Bean(name = "masterSqlSessionFactory")
 @Primary
 public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
  SqlSessionFactoryBean sessionFactory=new SqlSessionFactoryBean();
  sessionFactory.setDataSource(masterDataSource);
  return sessionFactory.getObject();
 }
}
