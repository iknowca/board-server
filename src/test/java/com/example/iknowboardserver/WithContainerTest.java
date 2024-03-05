package com.example.iknowboardserver;

import com.example.iknowboardserver.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.testcontainers.containers.MySQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RequiredArgsConstructor
public class WithContainerTest {
    protected static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    static {
        mySQLContainer.start();
        System.setProperty("spring.datasource.url", mySQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", mySQLContainer.getUsername());
        System.setProperty("spring.datasource.password", mySQLContainer.getPassword());

        Connection con;
        try {
            con = DriverManager.getConnection(
                    mySQLContainer.getJdbcUrl(),
                    mySQLContainer.getUsername(),
                    mySQLContainer.getPassword()
            );
            ScriptUtils.executeSqlScript(con, new ClassPathResource("createTable.sql"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
