package com.radnoti.meetwave.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DashboardService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> countBannedUsers() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("countBannedUsers");

        SqlParameterSource in = new MapSqlParameterSource();

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> countEvent() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("countEvent");

        SqlParameterSource in = new MapSqlParameterSource();

        return simpleJdbcCall.execute(in);
    }


    public Map<String, Object> countMutedUsers() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("countMutedUsers");

        SqlParameterSource in = new MapSqlParameterSource();

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> countUser() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("countUser");

        SqlParameterSource in = new MapSqlParameterSource();

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> countUserSubscriptions() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("countUserSubscriptions");

        SqlParameterSource in = new MapSqlParameterSource();

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> subscriptionLog(Integer userIDIN, Boolean statusIN, Integer typeOfSubscriptionIN) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("subscriptionLog");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("userIDIN", userIDIN);
        inParamMap.put("statusIN", statusIN);
        inParamMap.put("typeOfSubscriptionIN", typeOfSubscriptionIN);

        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }



}
