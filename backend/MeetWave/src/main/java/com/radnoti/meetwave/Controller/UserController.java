package com.radnoti.meetwave.Controller;

import com.radnoti.meetwave.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
