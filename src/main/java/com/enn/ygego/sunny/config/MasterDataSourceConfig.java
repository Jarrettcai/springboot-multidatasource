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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;
import java.sql.SQLException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages =MasterDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "masterSqlSessionFactory")
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

 @Value("${spring.datasource.filters}")
 private String filters;

 @Value("${spring.datasource.connectionProperties}")
 private String connectionProperties;

 @Bean(name = "masterDataSource")
 @Primary
 public DataSource masterDataSource(){
  DruidDataSource dataSource=new DruidDataSource();
  dataSource.setUrl(url);
  dataSource.setUsername(username);
  dataSource.setPassword(password);
  dataSource.setDriverClassName(driverClassName);
  try {
   dataSource.setFilters(filters);
  } catch (SQLException e) {
   e.printStackTrace();
  }
  dataSource.setConnectionProperties(connectionProperties);
  return dataSource;
 }

 @Bean(name = "masterTransactionManager")
 @Primary
 public DataSourceTransactionManager masterTransactionManager(){
   return  new DataSourceTransactionManager(masterDataSource());
 }
 @Bean(name = "masterSqlSessionFactory")
 @Primary
 public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception {
  SqlSessionFactoryBean sessionFactory=new SqlSessionFactoryBean();
  sessionFactory.setDataSource(masterDataSource);
  sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MasterDataSourceConfig.MAPPER_LOCATION));
  return sessionFactory.getObject();
 }
}
