package com.manuel.tfg.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://db5015951811.hosting-data.io:3306/dbs12999236");
        dataSource.setUsername("dbu1545302");
        dataSource.setPassword(".4we@zMKKztCNrj");
        return dataSource;
    }
}
