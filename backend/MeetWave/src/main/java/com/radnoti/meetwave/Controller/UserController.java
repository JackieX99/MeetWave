package com.radnoti.meetwave.Controller;

import com.radnoti.meetwave.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userservice;

    @Autowired
    public UserController(UserService userService) {
        this.userservice = userService;
    }


    @PostMapping("/getUser")
    public ResponseEntity<Map<String, Object>> getUser(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");

        Map<String, Object> result = userservice.getUserDataTest(userId);
        return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
    }

    @PostMapping("/muteUser")
    public ResponseEntity<Map<String, Object>> muteUser(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        try {
            userservice.muteUser(userId);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/uploadProfile")
    public ResponseEntity<Map<String, Object>> uploadImage(
            @RequestParam("userId") int userId,
            @RequestParam("file") MultipartFile imageFile) {

        Map<String, Object> result = new HashMap<>();

        // Ellenőrizzük, hogy a fájl tényleg kép-e és a megfelelő formátumban van-e
        if (!imageFile.getContentType().equals(MediaType.IMAGE_PNG_VALUE) && !imageFile.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)) {
            result.put("status", "failed");
            result.put("error", "Csak PNG vagy JPG formátumú képek engedélyezettek.");
            return ResponseEntity.badRequest().body(result);
        }

        userservice.uploadProfilePicture(userId, imageFile);

        result.put("status", "success");
        result.put("message", "A kép sikeresen feltöltve.");
        return ResponseEntity.ok(result);

    }
}
