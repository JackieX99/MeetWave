package com.radnoti.meetwave.Controller;

import com.radnoti.meetwave.Model.ErrorRes;
import com.radnoti.meetwave.Model.LoginReq;
import com.radnoti.meetwave.Model.LoginRes;
import com.radnoti.meetwave.Model.User;
import com.radnoti.meetwave.Service.CustomUserDetailsService;
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
@RequestMapping("/rest/auth")
public class HomeController {

    private final AuthenticationManager authenticationManager;
    private CustomUserDetailsService cuds;


    private JwtUtil jwtUtil;
    public HomeController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    @GetMapping("/getUserData")
    public ResponseEntity<Map<String, Object>> getUser(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        Map<String, Object> result = new HashMap<>();

        String token = extractTokenFromAuthorizationHeader(authorizationHeader);

        Claims decodedToken = jwtUtil.decodeJwt(token);

        String email = jwtUtil.getEmail(decodedToken);

        result.put("result", email);

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

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();
            User user = new User(email,"");
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(email,token);

            return ResponseEntity.ok(loginRes);

        } catch (BadCredentialsException e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
