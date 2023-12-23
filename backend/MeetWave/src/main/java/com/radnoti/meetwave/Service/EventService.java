package com.radnoti.meetwave.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class EventService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EventService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Map<String, Object> createEvent(String eventTitle, String description, java.util.Date dateOfTheEvent, String place, String founder, Timestamp dateOfCreatingEvent, int maxParticipants) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("createEvent");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("eventTitle", eventTitle);
        inParamMap.put("description", description);
        inParamMap.put("dateOfTheEvent", dateOfTheEvent);
        inParamMap.put("place", place);
        inParamMap.put("founder", founder);
        inParamMap.put("dateOfCreatingEvent", dateOfCreatingEvent);
        inParamMap.put("maxParticipants", maxParticipants);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> deleteComment(int commentID) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("deleteComment");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("commentID", commentID);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> deleteEvent(int eventID) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("deleteEvent");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("eventID", eventID);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> getAllEvent() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getAllEvent");

        SqlParameterSource in = new MapSqlParameterSource();

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> postComment(int eventID, int userID, String userCommentIN ,java.util.Date dateOfCommentIN) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("postComment");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("eventID", eventID);
        inParamMap.put("userID", userID);
        inParamMap.put("userCommentIN", userCommentIN);
        inParamMap.put("dateOfCommentIN", dateOfCommentIN);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> updateEvent(int eventID, String eventTitleIN, String descriptionIN ,java.util.Date dateOfTheEventIN, String placeOfTheEventIN, String founderOfTheEventIN, int maxParticipantsIN) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("updateEvent");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("eventID", eventID);
        inParamMap.put("eventTitleIN", eventTitleIN);
        inParamMap.put("descriptionIN", descriptionIN);
        inParamMap.put("dateOfTheEventIN", dateOfTheEventIN);
        inParamMap.put("placeOfTheEventIN", placeOfTheEventIN);
        inParamMap.put("founderOfTheEventIN", founderOfTheEventIN);
        inParamMap.put("maxParticipantsIN", maxParticipantsIN);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }


    }