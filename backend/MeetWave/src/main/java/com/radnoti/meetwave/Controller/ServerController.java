package com.radnoti.meetwave.Controller;

import com.radnoti.meetwave.Service.ServerService;
import com.radnoti.meetwave.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/server")
public class ServerController {

    private final ServerService server;

    @Autowired
    public ServerController(ServerService server) {
        this.server = server;
    }

    @PostMapping("/status")
    public ResponseEntity<Map<String, Object>> getUser(@RequestBody Map<String, Integer> requestBody) {
        Map<String, Object> result = server.CheckServerStatus();

        System.out.println(result);

        result.put("status", "success");

        return ResponseEntity.ok(result);
    }
}
