package com.radnoti.meetwave.Controller;


import com.radnoti.meetwave.Model.subscriptionLogClass;
import com.radnoti.meetwave.Model.userInterestClass;
import com.radnoti.meetwave.Service.DashboardService;
import com.radnoti.meetwave.Service.EventService;
import com.radnoti.meetwave.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final EventService eventService;
    private final UserService userservice;

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(EventService eventService, UserService userservice, DashboardService dashboardService) {
        this.eventService = eventService;
        this.userservice = userservice;
        this.dashboardService = dashboardService;
    }

    @GetMapping("/countBannedUsers")
    public ResponseEntity<Map<String, Object>> countBannedUsers() {

        Map<String, Object> result = dashboardService.countBannedUsers();
        System.out.println(result);
        return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
    }

    @GetMapping("/countEvent")
    public ResponseEntity<Map<String, Object>> countEvent() {

        Map<String, Object> result = dashboardService.countEvent();
        System.out.println(result);
        return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
    }


    @GetMapping("/countMutedUsers")
    public ResponseEntity<Map<String, Object>> countMutedUsers() {

        Map<String, Object> result = dashboardService.countMutedUsers();
        System.out.println(result);
        return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
    }

    @GetMapping("/countUser")
    public ResponseEntity<Map<String, Object>> countUser() {

        Map<String, Object> result = dashboardService.countUser();
        System.out.println(result);
        return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
    }

    @GetMapping("/countUserSubscriptions")
    public ResponseEntity<Map<String, Object>> countUserSubscriptions() {

        Map<String, Object> result = dashboardService.countUserSubscriptions();
        System.out.println(result);
        return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
    }

    @PostMapping("/subscriptionLog")
    public ResponseEntity<Map<String, Object>> subscriptionLog(@RequestBody subscriptionLogClass requestBody) {
        Integer userIDIN = requestBody.getUserIDIN();
        Boolean statusIN = requestBody.getStatusIN();
        Integer typeOfSubscriptionIN = requestBody.getTypeOfSubscriptionIN();

        Map<String, Object> result = new HashMap<>();

        try {
            dashboardService.subscriptionLog(userIDIN, statusIN, typeOfSubscriptionIN);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }




    }
