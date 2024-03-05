package com.example.iknowboardserver.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

@DisplayName("MyBatis 설정 테스트")
public class MyBatisTest {
    @Test
    void DB연결확인() {
        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://iknow4800h.site:8999/ibs_test?allowpublickeyretrieval=true&useSSl=false&serverTimezone=UTC",
                        "iknow",
                        "iknow@1234"
                ))
        {
            System.out.println(con);
        }
        catch(Exception e) {

        }
    }
}
