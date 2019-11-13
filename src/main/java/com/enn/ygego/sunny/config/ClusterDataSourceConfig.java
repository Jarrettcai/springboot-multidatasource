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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "clusterDataSource")
public class ClusterDataSourceConfig {
    static  final String PACKAGE="com.enn.ygego.sunny.dao.cluster";
    static final String MAPPER_LOCATION="classpath:mapper/cluster/*.xml";
    @Value("${cluster.datasource.url}")
    private String url;

    @Value("${cluster.datasource.username}")
    private String username;

    @Value("${cluster.datasource.password}")
    private String password;

    @Value("${cluster.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Bean(name = "clusterDataSource")
    @Primary
    public DataSource clusterDataSource(){
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

    @Bean(name = "clusterTransactionManager")
    @Primary
    public DataSourceTransactionManager clusterTransactionManager(){
        return  new DataSourceTransactionManager(clusterDataSource());
    }
    @Bean(name = "clusterSqlSessionFactory")
    @Primary
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource masterDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory=new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }



}
