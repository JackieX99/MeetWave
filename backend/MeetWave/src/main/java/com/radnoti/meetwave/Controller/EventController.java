package com.radnoti.meetwave.Controller;

import com.radnoti.meetwave.Model.AddUpdateReactionClass;
import com.radnoti.meetwave.Model.createEventClass;
import com.radnoti.meetwave.Model.postCommentClass;
import com.radnoti.meetwave.Model.updateEventClass;
import com.radnoti.meetwave.Service.EventService;
import com.radnoti.meetwave.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;
    private final UserService userservice;

    @Autowired
    public EventController(EventService eventService, UserService userservice) {
        this.eventService = eventService;
        this.userservice = userservice;
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
        String ticketLink = requestBody.getTicketLink();
        java.util.Date endOfEvent = requestBody.getEndOfEvent();
        String address = requestBody.getAddress();
        Boolean typeOfEvent = requestBody.getTypeOfEvent();
        Integer countInterested = requestBody.getCountInterested();
        Integer countWillBeThere = requestBody.getCountWillBeThere();

        Map<String, Object> result = new HashMap<>();

        try {
            eventService.createEvent(eventTitle, description, dateOfTheEvent, place, founder, dateOfCreatingEvent, maxParticipants, ticketLink , endOfEvent, address, typeOfEvent ,countInterested, countWillBeThere);

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
        String ticketsLinkIN = requestBody.getTicketsLinkIN();
        java.util.Date endOfEventIN = requestBody.getEndOfEventIN();
        String addressIN = requestBody.getAddressIN();
        Boolean typeOfEventIN = requestBody.getTypeOfEventIN();
        Integer countInterestedIN = requestBody.getCountInterestedIN();
        Integer countWillBeThereIN = requestBody.getCountWillBeThereIN();

        Map<String, Object> result = new HashMap<>();

        try {
            eventService.updateEvent(eventID, eventTitleIN, descriptionIN, dateOfTheEventIN, placeOfTheEventIN, founderOfTheEventIN, maxParticipantsIN, ticketsLinkIN, endOfEventIN, addressIN, typeOfEventIN, countInterestedIN, countWillBeThereIN);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/deleteComment")
    public ResponseEntity<Map<String, Object>> deleteComment(@RequestBody Map<String, Integer> requestBody, @RequestHeader(name = "Authorization") String authorizationHeader) {
        Integer commentID = requestBody.get("commentID");
        Map<String, Object> result = new HashMap<>();

        // headerből token kiszedés, eleje levágása
        String token = authorizationHeader.replace("Bearer ", "");
        // tokenből megkeressük az adott user email címét
        String emailFromToken = userservice.getUserEmailFromToken(token);
        // email alapján check, hogy admin-e
        Map<String, Object> isUserAdmin = userservice.isUserAdmin(emailFromToken);
        List<Map<String, Object>> isUserAdminResult = (List<Map<String, Object>>) isUserAdmin.get("#result-set-1");
        boolean isAdmin = ((Boolean) isUserAdminResult.get(0).get("isAdmin")).booleanValue();

        if(!isAdmin){
            result.put("status", "failed");
            result.put("error", "Admin jogosultság szükséges");
            return ResponseEntity.badRequest().body(result);
        }

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
    public ResponseEntity<Map<String, Object>> deleteEvent(@RequestBody Map<String, Integer> requestBody, @RequestHeader(name = "Authorization") String authorizationHeader) {
        Integer eventID = requestBody.get("eventID");
        Map<String, Object> result = new HashMap<>();

        // headerből token kiszedés, eleje levágása
        String token = authorizationHeader.replace("Bearer ", "");
        // tokenből megkeressük az adott user email címét
        String emailFromToken = userservice.getUserEmailFromToken(token);
        // email alapján check, hogy admin-e
        Map<String, Object> isUserAdmin = userservice.isUserAdmin(emailFromToken);
        List<Map<String, Object>> isUserAdminResult = (List<Map<String, Object>>) isUserAdmin.get("#result-set-1");
        boolean isAdmin = ((Boolean) isUserAdminResult.get(0).get("isAdmin")).booleanValue();

        if(!isAdmin){
            result.put("status", "failed");
            result.put("error", "Admin jogosultság szükséges");
            return ResponseEntity.badRequest().body(result);
        }

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


    @PostMapping("/AddUpdateReaction")
    public ResponseEntity<Map<String, Object>> AddUpdateReaction(@RequestBody AddUpdateReactionClass requestBody) {
        Integer commentIDIN = requestBody.getCommentIDIN();
        Integer eventIDIN = requestBody.getEventIDIN();
        Integer userIDIN = requestBody.getUserIDIN();
        Integer reactionTypeIN = requestBody.getReactionTypeIN();

        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy az eventID értéke érvényes
            if (eventIDIN == null || eventIDIN <= 0) {
                result.put("status", "failed");
                result.put("error", "Invalid eventID. Must be greater than 0.");
                return ResponseEntity.badRequest().body(result);
            }

            eventService.AddUpdateReaction(commentIDIN, eventIDIN, userIDIN,reactionTypeIN);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/getAllReactionsByComment")
    public ResponseEntity<Map<String, Object>> getAllReactionsByComment(@RequestBody Map<String, Integer> requestBody) {
        Integer CommentID = requestBody.get("CommentID");

        if (CommentID != null && CommentID >= 1) {
            Map<String, Object> result = eventService.getAllReactionsByComment(CommentID);
            System.out.println(result);
            return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
        } else {
            // Hibás kérés válasza, mert a userId nem lehet negatív
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("failed", "Nem lehet negatív a CommentID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/getEventInformations")
    public ResponseEntity<Map<String, Object>> getEventInformations(@RequestBody Map<String, Integer> requestBody) {
        Integer eventId = requestBody.get("eventId");
        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy a eventId értéke érvényes
            if (eventId == null || eventId <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív az eventId");
                return ResponseEntity.badRequest().body(result);
            }

            Map<String, Object> comments = eventService.getEventInformations(eventId);

            result.put("status", "success");
            result.put("comments", comments.get("#result-set-1"));
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }





}

