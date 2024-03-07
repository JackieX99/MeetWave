package com.radnoti.meetwave.Controller;

import com.radnoti.meetwave.Model.*;
import com.radnoti.meetwave.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userservice;

    @Autowired
    public UserController(UserService userService) {
        this.userservice = userService;
    }


    @RequestMapping(value = "",method = RequestMethod.GET)
    public String hello(){
        return "hello world";
    }

    /*
    @PostMapping("/getUser")
    public ResponseEntity<Map<String, Object>> getUser(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");

        if (userId != null && userId >= 1) {
            Map<String, Object> result = userservice.getUserData(userId);
            System.out.println(result);
            return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
        } else {
            // Hibás kérés válasza, mert a userId nem lehet negatív
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("failed", "Nem lehet negatív a UserID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
     */



    // 1. PostMapping vagy GetMapping
    // 2. Megadod az endpoint elérését
    // 3. Bemenő paraméterek beállítása
    // 3.1 ha több az adat mint mondjuk string és int párok, akkor salát class
    // 3.2 ha csak egy adott fajta bemenő, pl. string és hozzá egy int, akkor Map<String, Integer>
    // 4. bemenő paraméterek kigyűjtése a requestBody változóból saját változókba
    // 5. controllerhez tartozó service fájlban függvény létrehozása ami meghívja a tároltat
    // 6. controllerből service változó meghívása, annak átadni az endpoint bemenő paramétereit
    // 7. service függvényben beállítani hogy melyik tároltat hívja meg a függvény, és a bemenő paramétereit
    // 8. service függvényben return a meghívott tároltat
    // 9. controller függvény végén a result változóban tárolni a meghívott service függvény visszatérő értékét
    // 9.1. controller függvényből visszaadni (return) a result tartalmát, ha pl sok adatot vár vissza a frontend
    // 9.2. controller függvényből feltölteni a result változót a megfelelő adatokkal, és visszaadni, ha csak status-success, vagy status failed kell

    // SORREND
    // Controller függvény bejön az adat
    // Controller függvény átadja az adatot a service függvénynek
    // Service függvény meghívja a tároltat, átadja neki az adatokat
    // Service függvény visszakapja a tárolt eredményét, amit egyből visszaad a controllernek
    // Controller megkapja a választ, az alapján visszaadja a választ az endpointnak


    @PostMapping("/muteUser")
    public ResponseEntity<Map<String, Object>> muteUser(@RequestBody Map<String, Integer> requestBody,  @RequestHeader(name = "Authorization") String authorizationHeader) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        // headerből token kiszedés, eleje levágása
        String token = authorizationHeader.replace("Bearer ", "");
        // tokenből megkeressük az adott user email címét
        String emailFromToken = userservice.getUserEmailFromToken(token);
        // email alapján check, hogy admin-e
        Map<String, Object> isUserAdmin = userservice.isUserAdmin(emailFromToken);
        List<Map<String, Object>> isUserAdminResult = (List<Map<String, Object>>) isUserAdmin.get("#result-set-1");
        boolean isAdmin = ((Boolean) isUserAdminResult.get(0).get("isAdmin")).booleanValue();

        if(!isAdmin){
            result.put("status", "failed");
            result.put("error", "Admin jogosultság szükséges");
            return ResponseEntity.badRequest().body(result);
        }

        try {
            // Ellenőrizze, hogy a userId értéke érvényes
            if (userId == null || userId <= 1) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív a UserID");
                return ResponseEntity.badRequest().body(result);
            }

            userservice.muteUser(userId);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/unMuteUser")
    public ResponseEntity<Map<String, Object>> unMuteUser(@RequestBody Map<String, Integer> requestBody, @RequestHeader(name = "Authorization") String authorizationHeader) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        // headerből token kiszedés, eleje levágása
        String token = authorizationHeader.replace("Bearer ", "");
        // tokenből megkeressük az adott user email címét
        String emailFromToken = userservice.getUserEmailFromToken(token);
        // email alapján check, hogy admin-e
        Map<String, Object> isUserAdmin = userservice.isUserAdmin(emailFromToken);
        List<Map<String, Object>> isUserAdminResult = (List<Map<String, Object>>) isUserAdmin.get("#result-set-1");
        boolean isAdmin = ((Boolean) isUserAdminResult.get(0).get("isAdmin")).booleanValue();

        if(!isAdmin){
            result.put("status", "failed");
            result.put("error", "Admin jogosultság szükséges");
            return ResponseEntity.badRequest().body(result);
        }

        try {
            // Ellenőrizze, hogy a userId értéke érvényes
            if (userId == null || userId <= 1) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív a UserID");
                return ResponseEntity.badRequest().body(result);
            }

            userservice.unMuteUser(userId);

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

        // Ellenőrizzük, hogy a userId értéke érvényes
        if (userId <= 0) {
            result.put("status", "failed");
            result.put("error", "Nem lehet negatív a UserID");
            return ResponseEntity.badRequest().body(result);
        }

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

    @PostMapping("/banUser")
    public ResponseEntity<Map<String, Object>> banUser(@RequestBody Map<String, Integer> requestBody, @RequestHeader(name = "Authorization") String authorizationHeader) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        // headerből token kiszedés, eleje levágása
        String token = authorizationHeader.replace("Bearer ", "");
        // tokenből megkeressük az adott user email címét
        String emailFromToken = userservice.getUserEmailFromToken(token);
        // email alapján check, hogy admin-e
        Map<String, Object> isUserAdmin = userservice.isUserAdmin(emailFromToken);
        List<Map<String, Object>> isUserAdminResult = (List<Map<String, Object>>) isUserAdmin.get("#result-set-1");
        boolean isAdmin = ((Boolean) isUserAdminResult.get(0).get("isAdmin")).booleanValue();

        try {
            // Ellenőrizze, hogy a userId értéke érvényes
            if (userId == null || userId <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív a UserID");
                return ResponseEntity.badRequest().body(result);
            }

            userservice.banUser(userId);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/unBanUser")
    public ResponseEntity<Map<String, Object>> unBanUser(@RequestBody Map<String, Integer> requestBody, @RequestHeader(name = "Authorization") String authorizationHeader) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        // headerből token kiszedés, eleje levágása
        String token = authorizationHeader.replace("Bearer ", "");
        // tokenből megkeressük az adott user email címét
        String emailFromToken = userservice.getUserEmailFromToken(token);
        // email alapján check, hogy admin-e
        Map<String, Object> isUserAdmin = userservice.isUserAdmin(emailFromToken);
        List<Map<String, Object>> isUserAdminResult = (List<Map<String, Object>>) isUserAdmin.get("#result-set-1");
        boolean isAdmin = ((Boolean) isUserAdminResult.get(0).get("isAdmin")).booleanValue();

        try {
            // Ellenőrizze, hogy a userId értéke érvényes
            if (userId == null || userId <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív a UserID");
                return ResponseEntity.badRequest().body(result);
            }

            userservice.unBanUser(userId);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }


    @PostMapping("/deleteUser")
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody Map<String, Integer> requestBody, @RequestHeader(name = "Authorization") String authorizationHeader) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        // ez az email cím hívta meg az endpointot
        Map<String, Object> calledBy = userservice.getUserEmailById(userId);
        List<Map<String, Object>> calledByResult = (List<Map<String, Object>>) calledBy.get("#result-set-1");
        // ez tárolja a kiszedett email címet userId alapján lekérve
        String calledByEmail = ((String) calledByResult.get(0).get("email"));

        // headerből token kiszedés, eleje levágása
        String token = authorizationHeader.replace("Bearer ", "");
        // tokenből megkeressük az adott user email címét
        String emailFromToken = userservice.getUserEmailFromToken(token);
        // email alapján check, hogy admin-e
        Map<String, Object> isUserAdmin = userservice.isUserAdmin(emailFromToken);
        List<Map<String, Object>> isUserAdminResult = (List<Map<String, Object>>) isUserAdmin.get("#result-set-1");
        boolean isAdmin = ((Boolean) isUserAdminResult.get(0).get("isAdmin")).booleanValue();

        // ha nem egyezik a két email (aki meghívja, és akit editelni akar)
        if(!calledByEmail.equals(emailFromToken)){
            // ha nincs admin joga
            if(!isAdmin){
                result.put("status", "failed");
                result.put("error", "no perm");
                return ResponseEntity.badRequest().body(result);
            }
        }

        try {
            // Ellenőrizze, hogy a userId értéke érvényes
            if (userId == null || userId <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív a UserID");
                return ResponseEntity.badRequest().body(result);
            }

            userservice.deleteUser(userId);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody updateUserClass requestBody, @RequestHeader(name = "Authorization") String authorizationHeader) {
        Integer userId = requestBody.getUserID();
        String newFullName = requestBody.getNewFullName();
        String newEmail = requestBody.getNewEmail();
        String newPhoneNumber = requestBody.getNewPhoneNumber();
        Map<String, Object> result = new HashMap<>();

        // ez az email cím hívta meg az endpointot
        Map<String, Object> calledBy = userservice.getUserEmailById(userId);
        List<Map<String, Object>> calledByResult = (List<Map<String, Object>>) calledBy.get("#result-set-1");
        // ez tárolja a kiszedett email címet userId alapján lekérve
        String calledByEmail = ((String) calledByResult.get(0).get("email"));


        // headerből token kiszedés, eleje levágása
        String token = authorizationHeader.replace("Bearer ", "");
        // tokenből megkeressük az adott user email címét
        String emailFromToken = userservice.getUserEmailFromToken(token);
        // email alapján check, hogy admin-e
        Map<String, Object> isUserAdmin = userservice.isUserAdmin(emailFromToken);
        List<Map<String, Object>> isUserAdminResult = (List<Map<String, Object>>) isUserAdmin.get("#result-set-1");
        boolean isAdmin = ((Boolean) isUserAdminResult.get(0).get("isAdmin")).booleanValue();

        // ha nem egyezik a két email (aki meghívja, és akit editelni akar)
        if(!calledByEmail.equals(emailFromToken)){
            // ha nincs admin joga
            if(!isAdmin){
                result.put("status", "failed");
                result.put("error", "no perm");
                return ResponseEntity.badRequest().body(result);
            }
        }

        try {
            // Ellenőrizze, hogy a userId értéke érvényes
            if (userId == null || userId <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív a UserID");
                return ResponseEntity.badRequest().body(result);
            }

            // Ellenőrizze, hogy az új e-mail tartalmazza az '@' karaktert
            if (newEmail == null || !newEmail.contains("@")) {
                result.put("status", "failed");
                result.put("error", "Tartalmaznia kell @ karaktert az emailnek");
                return ResponseEntity.badRequest().body(result);
            }

            // Ellenőrizze, hogy az új telefonszám hossza nem haladja meg a 11 karaktert
            if (newPhoneNumber != null && newPhoneNumber.length() > 11) {
                result.put("status", "failed");
                result.put("error", "Nem elég hosszú a telefonszám");
                return ResponseEntity.badRequest().body(result);
            }

            userservice.updateUser(userId, newFullName, newEmail, newPhoneNumber);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/changeUserPermission")
    public ResponseEntity<Map<String, Object>> changeUserPermission(@RequestBody changeUserPermissionClass requestBody, @RequestHeader(name = "Authorization") String authorizationHeader) {
        Integer userId = requestBody.getUserID();
        Boolean isAdminIN = requestBody.getAdminIN();
        Map<String, Object> result = new HashMap<>();

        // headerből token kiszedés, eleje levágása
        String token = authorizationHeader.replace("Bearer ", "");
        // tokenből megkeressük az adott user email címét
        String emailFromToken = userservice.getUserEmailFromToken(token);
        // email alapján check, hogy admin-e
        Map<String, Object> isUserAdmin = userservice.isUserAdmin(emailFromToken);
        List<Map<String, Object>> isUserAdminResult = (List<Map<String, Object>>) isUserAdmin.get("#result-set-1");
        boolean isAdmin = ((Boolean) isUserAdminResult.get(0).get("isAdmin")).booleanValue();

        try {
            // Ellenőrizze, hogy a userId értéke érvényes
            if (userId == null || userId <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív a UserID");
                return ResponseEntity.badRequest().body(result);
            }

            userservice.changeUserPermission(userId, isAdminIN);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllBannedUsers")
    public ResponseEntity<Map<String, Object>> getAllBannedUsers() {

        Map<String, Object> result = userservice.getAllBannedUsers();
        System.out.println(result);
        return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
    }

    @GetMapping("/getAllMutedUsers")
    public ResponseEntity<Map<String, Object>> getAllMutedUsers() {

        Map<String, Object> result = userservice.getAllMutedUsers();
        System.out.println(result);
        return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
    }

    @PostMapping("/getUserSubscription")
    public ResponseEntity<Map<String, Object>> getUserSubscription(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy a userId értéke érvényes
            if (userId == null || userId <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív a UserID");
                return ResponseEntity.badRequest().body(result);
            }

            result = userservice.getUserSubscription(userId);
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody registerUserClass requestBody) {
        String fullNameIN = requestBody.getFullNameIN();
        String emailIN = requestBody.getEmailIN();
        String passwordIN = requestBody.getPasswordIN();
        String phoneNumberIN = requestBody.getPhoneNumberIN();
        Map<String, Object> result = new HashMap<>();


        Map<String, Object> userExist = userservice.checkIfUserExists(emailIN);
        List<Map<String, Object>> resultSetList = (List<Map<String, Object>>) userExist.get("#result-set-1");
        int userCount = ((Number) resultSetList.get(0).get("user_count")).intValue();
        if (userCount == 1) {
            result.put("status", "failed");
            result.put("error", "Email already in use.");
        } else {
            try {
                userservice.registerUser(fullNameIN, emailIN, passwordIN, phoneNumberIN);

                result.put("status", "success");
            } catch (Exception e) {
                result.put("status", "failed");
                result.put("error", e.getMessage());
            }
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody loginUserClass requestBody) {
        String emailIN = requestBody.getEmail();
        String passwordIN = requestBody.getPassword();

        Map<String, Object> result = new HashMap<>();

        Map<String, Object> loginResponse = userservice.loginUser(emailIN, passwordIN);
        // ha sikeres a bejelentkezés, szóval jó az email és jelszó páros
        if(loginResponse.get("result").equals("successful")){
            Map<String, Object> userData = userservice.getUserData(emailIN);
            result.put("status", "success");
            result.put("userdata", userData.get("#result-set-1"));
        } else{
            result.put("status", "failed");
            result.put("error", loginResponse.get("result"));
        }


        return ResponseEntity.ok(result);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Map<String, Object>> changePassword(@RequestBody changePasswordClass requestBody) {
        Integer userId = requestBody.getUserID();
        String newPassword = requestBody.getNewPassword();
        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy a userId értéke érvényes
            if (userId == null || userId <= 0) {
                result.put("status", "failed");
                result.put("error", "Invalid userId. Must be greater than 0.");
                return ResponseEntity.badRequest().body(result);
            }

            userservice.changePassword(userId, newPassword);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/setSub")
    public ResponseEntity<Map<String, Object>> setSubscription(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");
        Integer subscription = requestBody.get("subscription");

        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy a userId értéke érvényes
            if (userId == null || userId <= 0) {
                result.put("status", "failed");
                result.put("error", "Invalid userId. Must be greater than 0.");
                return ResponseEntity.badRequest().body(result);
            }

            Map<String, Object> storedResult = userservice.setSubscription(userId, subscription);
            System.out.println(storedResult);

            result.put("status", storedResult.get("result"));
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/profilePictureDelete")
    public ResponseEntity<Map<String, Object>> profilePictureDelete(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy a userId értéke érvényes
            if (userId == null || userId <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív a UserID");
                return ResponseEntity.badRequest().body(result);
            }

            userservice.profilePictureDelete(userId);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/subscriptionExtendDate")
    public ResponseEntity<Map<String, Object>> subscriptionExtendDate(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy a userId értéke érvényes
            if (userId == null || userId <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív a UserID");
                return ResponseEntity.badRequest().body(result);
            }

            userservice.subscriptionExtendDate(userId);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateComment")
    public ResponseEntity<Map<String, Object>> updateComment(@RequestBody updateCommentClass requestBody) {
        Integer userCommentID = requestBody.getUserCommentID();
        String userCommentIN = requestBody.getUserCommentIN();
        Map<String, Object> result = new HashMap<>();

        try {
            // Ellenőrizze, hogy a userCommentID értéke érvényes
            if (userCommentID == null || userCommentID <= 0) {
                result.put("status", "failed");
                result.put("error", "Nem lehet negatív a UserID");
                return ResponseEntity.badRequest().body(result);
            }

            userservice.updateComment(userCommentID, userCommentIN);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/getUserParticipate")
    public ResponseEntity<Map<String, Object>> getUserParticipate(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");

        if (userId != null && userId >= 1) {
            Map<String, Object> result = userservice.getUserParticipate(userId);
            System.out.println(result);
            return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
        } else {
            // Hibás kérés válasza, mert a userId nem lehet negatív
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("failed", "Nem lehet negatív a UserID");
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}