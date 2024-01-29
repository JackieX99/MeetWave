package com.radnoti.meetwave.Controller;

import com.radnoti.meetwave.Model.*;
import com.radnoti.meetwave.Service.CustomUserDetailsService;
import com.radnoti.meetwave.Service.EmailService;
import com.radnoti.meetwave.Service.UserService;
import com.radnoti.meetwave.Util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private CustomUserDetailsService cuds;
    private UserService userService;

    private EmailService emailService;


    private JwtUtil jwtUtil;
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService us, EmailService es) {
        this.authenticationManager = authenticationManager;
        this.userService = us;
        this.jwtUtil = jwtUtil;
        this.emailService = es;
    }

    @GetMapping("/getUserData")
    public ResponseEntity<Map<String, Object>> getUser(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        Map<String, Object> result = new HashMap<>();

        String token = extractTokenFromAuthorizationHeader(authorizationHeader);

        Claims decodedToken = jwtUtil.decodeJwt(token);

        String email = jwtUtil.getEmail(decodedToken);

        Map<String, Object> userData = userService.getUserData(email);
        result.put("status", "success");
        result.put("userdata", userData.get("#result-set-1"));

        return ResponseEntity.ok(result);
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody Map<String, String> requestBody) {
        Map<String, Object> result = new HashMap<>();

        String email = requestBody.get("targetEmail");

        System.out.println(email);

        Map<String, Object> emailResult = emailService.sendEmail(email);

        System.out.println(emailResult);

        result.put("status", "success");

        return ResponseEntity.ok(result);
    }

    private String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // "Bearer " hossza
        }
        return null;
    }


    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq loginReq)  {
        // endpointra bejövő adatok szétszedése
        String email = loginReq.getEmail();
        String passw = loginReq.getPassword();

        // válasz változó előre létrehozása több válasz lehetőség miatt
        Map<String, Object> result = new HashMap<>();

        // login ellenőrzése
        Map<String, Object> userExist = userService.loginUser(email, passw);
        String successLogin = (String) userExist.get("resultstatus");

        // ha jók a bejelentkezés adatok, visszaadjuk a tokent és hogy success
        if(successLogin.equals("successful")){
            User user = new User(email, passw);
            String token = jwtUtil.createToken(user);
            result.put("token", token);
            result.put("status", "success");
            result.put("email", email);
        } else{
            result.put("status", "failed");
        }

        return ResponseEntity.ok(result);
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegistrationRequest registrationRequest) {
        // endpointra bejövő adatok szétszedése
        String name =  registrationRequest.getFullNameIN();
        String emailIn = registrationRequest.getEmailIN();
        String passw = registrationRequest.getPasswordIN();
        String phone =  registrationRequest.getPhoneNumberIN();

        // válasz változó előre létrehozása több válasz lehetőség miatt
        Map<String, Object> result = new HashMap<>();

        // ellenőrzés, hogy foglalt-e már az adott email cím
        Map<String, Object> userExist = userService.checkIfUserExists(emailIn);
        Boolean emailExists = (Boolean) userExist.get("userExists");

        // ha foglalt visszadobjuk hogy foglalt
        if (emailExists) {
            result.put("status", "failed");
            result.put("error", "Email already in use.");
        }

        // ha nem foglalt, akkor regisztráció
        else {
            try {
                userService.registerUser(name, emailIn, passw, phone);
                User user = new User(emailIn, passw);
                String token = jwtUtil.createToken(user);
                result.put("token", token);
                result.put("status", "success");
                result.put("email", emailIn);
            } catch (Exception e) {
                result.put("status", "failed");
                result.put("error", e.getMessage());
            }
        }

        return ResponseEntity.ok(result);
    }

}
