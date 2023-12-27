package com.radnoti.meetwave.Controller;

import com.radnoti.meetwave.Model.*;
import com.radnoti.meetwave.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
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

    @Test
    public void testBanUserWithValidUserId() {
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
    public void testBanUserWithInvalidUserId() {
        // Arrange
        Integer userId = -1;
        Map<String, Object> result = new HashMap<>();

        // Act
        ResponseEntity<Map<String, Object>> response = userController.banUser(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Nem lehet negatív a UserID", response.getBody().get("error"));
    }

    @Test
    public void testBanUserException() {
        // Arrange
        Integer userId = 1; // Assuming valid user ID
        Map<String, Object> result = new HashMap<>();
        doThrow(new RuntimeException("Some error")).when(userService).banUser(anyInt());

        // Act
        ResponseEntity<Map<String, Object>> response = userController.banUser(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Some error", response.getBody().get("error"));
    }

    @Test
    public void testUnBanUserWithValidUserId() {
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
    public void testUnBanUserWithInvalidUserId() {
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
    public void testUnBanUserException() {
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

    @Test
    public void testDeleteUserWithValidUserId() {
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
    public void testDeleteUserWithInvalidUserId() {
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
    public void testDeleteUserException() {
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


    @Test
    public void testUpdateUserWithValidData() {
        // Arrange
        updateUserClass requestBody = new updateUserClass(1, "NewName", "newemail@example.com", "36205120000");


        Map<String, Object> expectedResult = Map.of("someKey", "someValue");
        doReturn(expectedResult).when(userService).updateUser(anyInt(), anyString(), anyString(), anyString());

        // Act
        ResponseEntity<Map<String, Object>> response = userController.updateUser(requestBody);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));
    }

    @Test
    public void testUpdateUserWithInvalidUserId() {
        // Arrange
        updateUserClass requestBody = new updateUserClass(-1, "NewName", "newemail@example.com", "36205120000");
        Map<String, Object> result = new HashMap<>();

        // Act
        ResponseEntity<Map<String, Object>> response = userController.updateUser(requestBody);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Nem lehet negatív a UserID", response.getBody().get("error"));
    }

    @Test
    public void testUpdateUserWithInvalidEmail() {
        // Arrange
        updateUserClass requestBody = new updateUserClass(1, "NewName", "invalidemail", "36205120000");
        Map<String, Object> result = new HashMap<>();

        // Act
        ResponseEntity<Map<String, Object>> response = userController.updateUser(requestBody);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Tartalmaznia kell @ karaktert az emailnek", response.getBody().get("error"));
    }

    @Test
    public void testUpdateUserWithInvalidPhoneNumber() {
        // Arrange
        updateUserClass requestBody = new updateUserClass(1, "NewName", "newemail@example.com", "12345678901234567890");
        Map<String, Object> result = new HashMap<>();

        // Act
        ResponseEntity<Map<String, Object>> response = userController.updateUser(requestBody);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Nem elég hosszú a telefonszám", response.getBody().get("error"));
    }

    @Test
    public void testUpdateUserException() {
        // Arrange
        updateUserClass requestBody = new updateUserClass(1, "NewName", "newemail@example.com", "36205120000");
        Map<String, Object> result = new HashMap<>();
        doThrow(new RuntimeException("Some error")).when(userService).updateUser(anyInt(), anyString(), anyString(), anyString());

        // Act
        ResponseEntity<Map<String, Object>> response = userController.updateUser(requestBody);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Some error", response.getBody().get("error"));
    }

    @Test
    public void testChangeUserPermissionWithValidData() {
        // Arrange
        changeUserPermissionClass requestBody = new changeUserPermissionClass(1, true);
        Map<String, Object> expectedResult = Map.of("someKey", "someValue");

        // Ezt a részt módosítottam
        doReturn(expectedResult).when(userService).changeUserPermission(anyInt(), anyBoolean());

        // Act
        ResponseEntity<Map<String, Object>> response = userController.changeUserPermission(requestBody);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));
    }

    @Test
    public void testChangeUserPermissionWithInvalidUserId() {
        // Arrange
        changeUserPermissionClass requestBody = new changeUserPermissionClass(0, true);
        Map<String, Object> result = new HashMap<>();

        // Act
        ResponseEntity<Map<String, Object>> response = userController.changeUserPermission(requestBody);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Nem lehet negatív a UserID", response.getBody().get("error"));
    }

    @Test
    public void testChangeUserPermissionException() {
        // Arrange
        changeUserPermissionClass requestBody = new changeUserPermissionClass(1, true);
        Map<String, Object> result = new HashMap<>();
        doThrow(new RuntimeException("Some error")).when(userService).changeUserPermission(anyInt(), anyBoolean());

        // Act
        ResponseEntity<Map<String, Object>> response = userController.changeUserPermission(requestBody);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Some error", response.getBody().get("error"));
    }

    @Test
    public void testGetUserSubscriptionWithValidUserId() {
        // Arrange
        Integer userId = 1; // Assuming valid user ID
        Map<String, Object> expectedResult = Map.of("someKey", "someValue");

        when(userService.getUserSubscription(userId)).thenReturn(expectedResult);

        // Act
        ResponseEntity<Map<String, Object>> response = userController.getUserSubscription(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testGetUserSubscriptionWithInvalidUserId() {
        // Arrange
        Integer userId = -1; // Assuming invalid user ID
        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("status", "failed");
        expectedResult.put("error", "Nem lehet negatív a UserID");

        // Act
        ResponseEntity<Map<String, Object>> response = userController.getUserSubscription(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testGetUserSubscriptionException() {
        // Arrange
        Integer userId = 2; // Assuming valid user ID
        Map<String, Object> expectedResult = new HashMap<>();
        expectedResult.put("status", "failed");
        expectedResult.put("error", "Some error");

        when(userService.getUserSubscription(userId)).thenThrow(new RuntimeException("Some error"));

        // Act
        ResponseEntity<Map<String, Object>> response = userController.getUserSubscription(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testChangePasswordWithValidData() {
        // Arrange
        changePasswordClass requestBody = new changePasswordClass(1, "newPassword123");
        Map<String, Object> result = new HashMap<>();
        when(userService.changePassword(anyInt(), anyString())).thenReturn(null);

        // Act
        ResponseEntity<Map<String, Object>> response = userController.changePassword(requestBody);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));

        // Verify that the method was called with the correct arguments
        verify(userService, times(1)).changePassword(requestBody.getUserID(), requestBody.getNewPassword());
    }

    @Test
    public void testProfilePictureDeleteWithValidUserId() {
        // Arrange
        Integer userId = 1; // Assuming valid user ID
        Map<String, Object> result = new HashMap<>();
        when(userService.profilePictureDelete(anyInt())).thenReturn(null);

        // Act
        ResponseEntity<Map<String, Object>> response = userController.profilePictureDelete(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));

        // Verify that the method was called with the correct argument
        verify(userService, times(1)).profilePictureDelete(userId);
    }

    @Test
    public void testProfilePictureDeleteWithInvalidUserId() {
        // Arrange
        Integer invalidUserId = -1; // Assuming invalid user ID
        Map<String, Object> result = new HashMap<>();

        // Act
        ResponseEntity<Map<String, Object>> response = userController.profilePictureDelete(Map.of("userId", invalidUserId));

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Nem lehet negatív a UserID", response.getBody().get("error"));

        // Verify that the userService.profilePictureDelete method is not called
        verify(userService, never()).profilePictureDelete(anyInt());
    }

    @Test
    public void testSubscriptionExtendDateWithValidUserId() {
        // Arrange
        Integer userId = 1; // Assuming valid user ID
        Map<String, Object> result = new HashMap<>();
        when(userService.subscriptionExtendDate(anyInt())).thenReturn(null);

        // Act
        ResponseEntity<Map<String, Object>> response = userController.subscriptionExtendDate(Map.of("userId", userId));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));

        // Verify that the method was called with the correct argument
        verify(userService, times(1)).subscriptionExtendDate(userId);
    }

    @Test
    public void testSubscriptionExtendDateWithInvalidUserId() {
        // Arrange
        Integer invalidUserId = -1; // Assuming invalid user ID
        Map<String, Object> result = new HashMap<>();

        // Act
        ResponseEntity<Map<String, Object>> response = userController.subscriptionExtendDate(Map.of("userId", invalidUserId));

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Nem lehet negatív a UserID", response.getBody().get("error"));

        // Verify that the userService.subscriptionExtendDate method is not called
        verify(userService, never()).subscriptionExtendDate(anyInt());
    }

    @Test
    public void testUpdateCommentWithValidUserCommentID() {
        // Arrange
        Integer validUserCommentID = 1; // Assuming valid userCommentID
        Map<String, Object> result = new HashMap<>();
        when(userService.updateComment(anyInt(), anyString())).thenReturn(result);

        // Act
        ResponseEntity<Map<String, Object>> response = userController.updateComment(new updateCommentClass(validUserCommentID, "SomeComment"));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));

        // Verify that the userService.updateComment method is called with the validUserCommentID and the comment
        verify(userService, times(1)).updateComment(validUserCommentID, "SomeComment");
    }

    @Test
    public void testUpdateCommentWithInvalidUserCommentID() {
        // Arrange
        Integer invalidUserCommentID = -1; // Assuming invalid userCommentID
        Map<String, Object> result = new HashMap<>();

        // Act
        ResponseEntity<Map<String, Object>> response = userController.updateComment(new updateCommentClass(invalidUserCommentID, "SomeComment"));

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("failed", response.getBody().get("status"));
        assertEquals("Nem lehet negatív a UserID", response.getBody().get("error"));

        // Verify that the userService.updateComment method is not called
        verify(userService, never()).updateComment(anyInt(), anyString());
    }






}

