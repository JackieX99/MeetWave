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


    public Map<String, Object> createEvent(String eventTitle, String description, java.util.Date dateOfTheEvent, String place, String founder, Timestamp dateOfCreatingEvent, int maxParticipants, String ticketLink, java.util.Date endOfEvent, String address, Boolean typeOfEvent, int countInterested, int countWillBeThere) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("createEvent");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("eventTitle", eventTitle);
        inParamMap.put("description", description);
        inParamMap.put("dateOfTheEvent", dateOfTheEvent);
        inParamMap.put("place", place);
        inParamMap.put("founder", founder);
        inParamMap.put("dateOfCreatingEvent", dateOfCreatingEvent);
        inParamMap.put("maxParticipants", maxParticipants);
        inParamMap.put("ticketLink", ticketLink);
        inParamMap.put("endOfEvent", endOfEvent);
        inParamMap.put("address", address);
        inParamMap.put("typeOfEvent", typeOfEvent);
        inParamMap.put("countInterested", countInterested);
        inParamMap.put("countWillBeThere", countWillBeThere);
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

    public Map<String, Object> getAllComment(int eventId) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getAllCommentByEvent");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("eventID", eventId);
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

    public Map<String, Object> userParticipateOnEvent(int eventid, int userid, int choice) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("userParticipateOnEvent");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("eventIDIN", eventid);
        inParamMap.put("userIDIN", userid);
        inParamMap.put("typeOfParticipationIN", choice);
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

    public Map<String, Object> updateEvent(int eventID, String eventTitleIN, String descriptionIN ,java.util.Date dateOfTheEventIN, String placeOfTheEventIN, String founderOfTheEventIN, int maxParticipantsIN, String ticketsLinkIN, java.util.Date endOfEventIN, String addressIN, Boolean typeOfEventIN, int countInterestedIN, int countWillBeThereIN) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("updateEvent");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("eventID", eventID);
        inParamMap.put("eventTitleIN", eventTitleIN);
        inParamMap.put("descriptionIN", descriptionIN);
        inParamMap.put("dateOfTheEventIN", dateOfTheEventIN);
        inParamMap.put("placeOfTheEventIN", placeOfTheEventIN);
        inParamMap.put("founderOfTheEventIN", founderOfTheEventIN);
        inParamMap.put("maxParticipantsIN", maxParticipantsIN);
        inParamMap.put("ticketsLinkIN", ticketsLinkIN);
        inParamMap.put("endOfEventIN", endOfEventIN);
        inParamMap.put("addressIN", addressIN);
        inParamMap.put("typeOfEventIN", typeOfEventIN);
        inParamMap.put("countInterestedIN", countInterestedIN);
        inParamMap.put("countWillBeThereIN", countWillBeThereIN);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> AddUpdateReaction(int commentIDIN, int eventIDIN, int userIDIN, int reactionTypeIN) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("AddUpdateReaction");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("commentIDIN", commentIDIN);
        inParamMap.put("eventIDIN", eventIDIN);
        inParamMap.put("userIDIN", userIDIN);
        inParamMap.put("reactionTypeIN", reactionTypeIN);

        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }

    public Map<String, Object> getAllReactionsByComment(int CommentID) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getAllReactionsByComment");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("CommentID", CommentID);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }


    public Map<String, Object> getEventInformations(int eventId) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getEventInformations");

        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("eventID", eventId);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.execute(in);
    }
    }
