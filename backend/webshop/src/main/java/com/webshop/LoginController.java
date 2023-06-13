package com.webshop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (isValidCredentials(request.getUsername(), request.getPassword())) {
            // Sikeres bejelentkezés
            return ResponseEntity.ok("Bejelentkezés sikeres");
        } else {
            // Hibás bejelentkezés
            String errorMessage = "Hibás felhasználónév vagy jelszó";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }
    }
    
    private boolean isValidCredentials(String username, String password) {
        // Ellenőrizd a bejelentkezési adatokat
        // Return true, ha helyesek, false, ha hibásak
        return true;
    }
}
