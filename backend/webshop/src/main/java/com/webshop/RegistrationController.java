package com.webshop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        if (!isValidRegistration(request)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorCode(400);
            errorResponse.setErrorMessage("Hibás regisztrációs adatok");
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        // Sikeres regisztráció logika

        return ResponseEntity.ok("Regisztráció sikeres");
    }

    private boolean isValidRegistration(RegistrationRequest request) {
        // Regisztrációs adatok ellenőrzése
        // Return true, ha a regisztráció helyes, false, ha hibás
        return true;
        
    }
}

