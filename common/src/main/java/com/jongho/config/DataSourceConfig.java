package com.jongho.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/*
* sourceDataSource라는 Bean 이름을 가진 DataSource를 생성하는 설정 클래스
*/
@Configuration
public class DataSourceConfig {
    @Bean(name = "sourceDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.source")
    public DataSource sourceDataSource() {

        // 빌더패턴을 사용한 DataSource 생성
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }
}