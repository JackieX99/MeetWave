package com.radnoti.meetwave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@EnableScheduling
public class MeetWaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetWaveApplication.class, args);
    }


    @Service
    public static class SubscriptionService {

        private final JdbcTemplate jdbcTemplate;

        public SubscriptionService(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Scheduled(fixedRate = 600000) // 10 perc = 600 000 milliszekundum
        public void checkSubscriptions() {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("checkExpiredSubscriptions");

            simpleJdbcCall.execute();


        }
    }


    }

