package com.radnoti.meetwave.Controller;

import com.radnoti.meetwave.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void testGetUserWithValidUserId() {
        // Arrange
        Integer userId = 1;
        Map<String, Object> userData = new HashMap<>();
        userData.put("#result-set-1", "MockedUserData");
        when(userService.getUserDataTest(anyInt())).thenReturn(userData);

        // Act
        ResponseEntity<Map<String, Object>> response = userController.getUser(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userData.get("#result-set-1"), response.getBody().get("userData"));
    }

    @Test
    public void testGetUserWithInvalidUserId() {
        // Arrange
        Integer userId = -1;

        // Act
        ResponseEntity<Map<String, Object>> response = userController.getUser(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Nem lehet negatív a UserID", response.getBody().get("failed"));
    }

    @Test
    public void testMuteUserWithValidUserId() {
        // Arrange
        Integer userId = 2; // Assuming valid user ID
        Map<String, Object> result = new HashMap<>();
        when(userService.muteUser(anyInt())).thenReturn(null);

        // Act
        ResponseEntity<Map<String, Object>> response = userController.muteUser(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));
    }

    @Test
    public void testMuteUserWithInvalidUserId() {
        // Arrange
        Integer userId = 1;
        Map<String, Object> result = new HashMap<>();

        // Act
        ResponseEntity<Map<String, Object>> response = userController.muteUser(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Nem lehet negatív a UserID", response.getBody().get("error"));
    }

    @Test
    public void testMuteUserException() {
        // Arrange
        Integer userId = 2; // Assuming valid user ID
        Map<String, Object> result = new HashMap<>();
        doThrow(new RuntimeException("Some error")).when(userService).muteUser(anyInt());

        // Act
        ResponseEntity<Map<String, Object>> response = userController.muteUser(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Some error", response.getBody().get("error"));
    }

    @Test
    public void testUnMuteUserWithValidUserId() {
        // Arrange
        Integer userId = 2; // Assuming valid user ID
        Map<String, Object> result = new HashMap<>();
        when(userService.unMuteUser(anyInt())).thenReturn(null);

        // Act
        ResponseEntity<Map<String, Object>> response = userController.unMuteUser(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));
    }

    @Test
    public void testUnMuteUserWithInvalidUserId() {
        // Arrange
        Integer userId = -1;
        Map<String, Object> result = new HashMap<>();

        // Act
        ResponseEntity<Map<String, Object>> response = userController.unMuteUser(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Nem lehet negatív a UserID", response.getBody().get("error"));
    }

    @Test
    public void testUnMuteUserException() {
        // Arrange
        Integer userId = 2; // Assuming valid user ID
        Map<String, Object> result = new HashMap<>();
        doThrow(new RuntimeException("Some error")).when(userService).unMuteUser(anyInt());

        // Act
        ResponseEntity<Map<String, Object>> response = userController.unMuteUser(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Some error", response.getBody().get("error"));
    }



}

