package com.radnoti.meetwave.Controller;

import com.radnoti.meetwave.Model.changePasswordClass;
import com.radnoti.meetwave.Model.createEventClass;
import com.radnoti.meetwave.Model.postCommentClass;
import com.radnoti.meetwave.Model.updateEventClass;
import com.radnoti.meetwave.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/createEvent")
    public ResponseEntity<Map<String, Object>> createEvent(@RequestBody createEventClass requestBody) {
        String eventTitle = requestBody.getEventTitle();
        String description = requestBody.getDescription();
        java.util.Date dateOfTheEvent = requestBody.getDateOfTheEvent();
        String place = requestBody.getPlace();
        String founder = requestBody.getFounder();
        Timestamp dateOfCreatingEvent = requestBody.getDateOfCreatingEvent();
        Integer maxParticipants = requestBody.getMaxParticipants();

        Map<String, Object> result = new HashMap<>();

        try {
            eventService.createEvent(eventTitle, description, dateOfTheEvent, place, founder, dateOfCreatingEvent, maxParticipants);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/deleteComment")
    public ResponseEntity<Map<String, Object>> deleteComment(@RequestBody Map<String, Integer> requestBody) {
        Integer commentID = requestBody.get("commentID");
        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy a commentID értéke érvényes
            if (commentID == null || commentID <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív a commentID");
                return ResponseEntity.badRequest().body(result);
            }

            eventService.deleteComment(commentID);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/getComments")
    public ResponseEntity<Map<String, Object>> getAllComment(@RequestBody Map<String, Integer> requestBody) {
        Integer eventId = requestBody.get("eventId");
        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy a eventId értéke érvényes
            if (eventId == null || eventId <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív az eventId");
                return ResponseEntity.badRequest().body(result);
            }

            Map<String, Object> comments = eventService.getAllComment(eventId);

            result.put("status", "success");
            result.put("comments", comments.get("#result-set-1"));
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }


    @PostMapping("/deleteEvent")
    public ResponseEntity<Map<String, Object>> deleteEvent(@RequestBody Map<String, Integer> requestBody) {
        Integer eventID = requestBody.get("eventID");
        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy az eventID értéke érvényes
            if (eventID == null || eventID <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív az eventID");
                return ResponseEntity.badRequest().body(result);
            }

            eventService.deleteEvent(eventID);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/userParticipate")
    public ResponseEntity<Map<String, Object>> userParticipateOnEvent(@RequestBody Map<String, Integer> requestBody) {
        Integer eventId = requestBody.get("eventId");
        Integer userId = requestBody.get("userId");
        Integer choice = requestBody.get("choice");
        Map<String, Object> result = new HashMap<>();

        try {

            eventService.userParticipateOnEvent(eventId, userId, choice);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }


    @GetMapping("/getAllEvent")
    public ResponseEntity<Map<String, Object>> getAllEvent() {

        Map<String, Object> result = eventService.getAllEvent();
        System.out.println(result);
        return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
    }

    @PostMapping("/postComment")
    public ResponseEntity<Map<String, Object>> postComment(@RequestBody postCommentClass requestBody) {
        Integer eventID = requestBody.getEventID();
        Integer userID = requestBody.getUserID();
        String userCommentIN = requestBody.getUserCommentIN();
        java.util.Date dateOfCommentIN = requestBody.getDateOfCommentIN();

        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy az eventID értéke érvényes
            if (eventID == null || eventID <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív az eventID");
                return ResponseEntity.badRequest().body(result);
            }

            // Ellenőrizze, hogy a userID értéke érvényes
            if (userID == null || userID <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív az userID");
                return ResponseEntity.badRequest().body(result);
            }

            eventService.postComment(eventID, userID, userCommentIN, dateOfCommentIN);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }


    @PostMapping("/updateEvent")
    public ResponseEntity<Map<String, Object>> updateEvent(@RequestBody updateEventClass requestBody) {
        Integer eventID = requestBody.getEventID();
        String eventTitleIN = requestBody.getEventTitleIN();
        String descriptionIN = requestBody.getDescriptionIN();
        java.util.Date dateOfTheEventIN = requestBody.getDateOfTheEventIN();
        String placeOfTheEventIN = requestBody.getPlaceOfTheEventIN();
        String founderOfTheEventIN = requestBody.getFounderOfTheEventIN();
        Integer maxParticipantsIN = requestBody.getMaxParticipantsIN();

        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy az eventID értéke érvényes
            if (eventID == null || eventID <= 0) {
                result.put("status", "failed");
                result.put("error", "Invalid eventID. Must be greater than 0.");
                return ResponseEntity.badRequest().body(result);
            }

            eventService.updateEvent(eventID, eventTitleIN, descriptionIN, dateOfTheEventIN, placeOfTheEventIN, founderOfTheEventIN, maxParticipantsIN);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }




}

