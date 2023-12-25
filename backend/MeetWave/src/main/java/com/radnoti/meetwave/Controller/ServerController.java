package com.radnoti.meetwave.Controller;

import com.radnoti.meetwave.Service.ServerService;
import com.radnoti.meetwave.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getUser() {
        Map<String, Object> status = server.CheckServerStatus();
        Map<String, Object> result = new HashMap<>();
        
        result.put("status", status.get("status"));

        return ResponseEntity.ok(result);
    }
}
