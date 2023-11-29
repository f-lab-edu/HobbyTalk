package com.jongho.common.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ActiveProfiles("test")
@MybatisTest(properties = "spring.profiles.active=test")
@MapperScan(basePackages = "com.jongho.**.dao.mapper")
@ContextConfiguration(classes = RepositoryTestConfiguration.class)
public class BaseMapperTest {
    @Autowired
    private DataSource dataSource;

    private void excuteTruncateTable(String tableName){
        String sql = "TRUNCATE TABLE " + tableName;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void initializeAutoIncrement(String tableName){
        String sql = "ALTER TABLE " + tableName + " ALTER COLUMN id RESTART WITH 1;";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    protected void cleanUpUserTable(){
        excuteTruncateTable("users");
        initializeAutoIncrement("users");
    }
    protected void cleanUpUserNotificationSettingTable(){
        excuteTruncateTable("user_notification_settings");
        initializeAutoIncrement("user_notification_settings");
    }
}