package com.radnoti.meetwave.Controller;

import com.radnoti.meetwave.Model.changeUserPermissionClass;
import com.radnoti.meetwave.Model.registerUserClass;
import com.radnoti.meetwave.Model.updateUserClass;
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

    @PostMapping("/getUser")
    public ResponseEntity<Map<String, Object>> getUser(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");

        Map<String, Object> result = userservice.getUserDataTest(userId);
        System.out.println(result);
        return ResponseEntity.ok(Map.of("userData", result.get("#result-set-1")));
    }

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

    @PostMapping("/unMuteUser")
    public ResponseEntity<Map<String, Object>> unMuteUser(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        try {
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
    public ResponseEntity<Map<String, Object>> banUser(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        try {
            userservice.banUser(userId);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/unBanUser")
    public ResponseEntity<Map<String, Object>> unBanUser(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        try {
            userservice.unBanUser(userId);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody Map<String, Integer> requestBody) {
        Integer userId = requestBody.get("userId");
        Map<String, Object> result = new HashMap<>();

        try {
            userservice.deleteUser(userId);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody updateUserClass requestBody) {
        Integer userId = requestBody.getUserID();
        String newFullName = requestBody.getNewFullName();
        String newEmail = requestBody.getNewEmail();
        String newPhoneNumber = requestBody.getNewPhoneNumber();
        Map<String, Object> result = new HashMap<>();

        try {
            userservice.updateUser(userId, newFullName, newEmail, newPhoneNumber);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/changeUserPermission")
    public ResponseEntity<Map<String, Object>> changeUserPermission(@RequestBody changeUserPermissionClass requestBody) {
        Integer userId = requestBody.getUserID();
        Boolean isAdminIN = requestBody.getAdminIN();
        Map<String, Object> result = new HashMap<>();

        try {
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

        Map<String, Object> result = userservice.getUserSubscription(userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody registerUserClass requestBody) {
        String fullNameIN = requestBody.getFullNameIN();
        String emailIN = requestBody.getEmailIN();
        String passwordIN = requestBody.getPasswordIN();
        String phoneNumberIN = requestBody.getPasswordIN();
        Map<String, Object> result = new HashMap<>();


        Map<String, Object> userExist = userservice.checkIfUserExists("foldvarialex@gmail.com");
        List<Map<String, Object>> resultSetList = (List<Map<String, Object>>) userExist.get("#result-set-1");
        int userCount = ((Number) resultSetList.get(0).get("user_count")).intValue();
        if(userCount == 1){
            System.out.println("Ez az email cím már foglalt.");
        } else{
            System.out.println("Nem foglalt, mehet a regisztráció.");
        }
        try {
            userservice.registerUser(fullNameIN,emailIN,passwordIN,phoneNumberIN);

            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }

        return ResponseEntity.ok(result);
    }
}
