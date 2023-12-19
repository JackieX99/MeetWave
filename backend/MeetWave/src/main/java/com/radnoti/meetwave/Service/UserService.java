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
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getUserData");

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

    public Map<String, Object> unMuteUser(int userId) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("unMuteUser");

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
    public Map<String, Object> banUser(int userId) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("banUser");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("userID", userId);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> unBanUser(int userId) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("unBanUser");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("userID", userId);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> deleteUser(int userId) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("deleteUser");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("userID", userId);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> updateUser(int userId, String newFullName, String newEmail, String newPhoneNumber) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("updateUser");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("userID", userId);
        inParamMap.put("newFullName", newFullName);
        inParamMap.put("newEmail", newEmail);
        inParamMap.put("newPhoneNumber", newPhoneNumber);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> changeUserPermission(int userId, boolean isAdminIN) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("changeUserPermission");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("userID", userId);
        inParamMap.put("isAdminIN", isAdminIN);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> getAllBannedUsers() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getAllBannedUsers");

        SqlParameterSource in = new MapSqlParameterSource();

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> getAllMutedUsers() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getAllMutedUsers");

        SqlParameterSource in = new MapSqlParameterSource();

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> getUserSubscription(int userId) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getUserSubscription");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("userID", userId);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> registerUser(String fullNameIN, String emailIN, String passwordIN, String phoneNumberIN) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("registerUser");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("fullNameIN", fullNameIN);
        inParamMap.put("emailIN", emailIN);
        inParamMap.put("passwordIN", passwordIN);
        inParamMap.put("phoneNumberIN", phoneNumberIN);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> checkIfUserExists(String p_email) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("checkIfUserExists");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("p_email", p_email);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }


}
