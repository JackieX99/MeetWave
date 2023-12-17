package com.radnoti.meetwave.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> getUserDataTest(int userId) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getUserDataTest");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("p_userID", userId);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> muteUser(int userId) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("muteUser");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("userID", userId);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> uploadProfilePicture(int userId, MultipartFile imageFile) {
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("profilePictureUpload");

            Map<String, Object> inParamMap = new HashMap<>();
            inParamMap.put("userID", userId);
            inParamMap.put("fileContent", imageFile.getBytes());
            SqlParameterSource in = new MapSqlParameterSource(inParamMap);

            return simpleJdbcCall.execute(in);
        } catch (IOException e) {
            // Hiba történt a fájl beolvasása során
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("status", "failed");
            errorResult.put("error", "Hiba történt a fájl beolvasása során.");
            return errorResult;
        }
    }
}
