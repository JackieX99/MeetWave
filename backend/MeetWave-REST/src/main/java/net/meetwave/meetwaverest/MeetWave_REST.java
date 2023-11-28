package net.meetwave.meetwaverest;

import com.google.gson.Gson;
import net.meetwave.meetwaverest.Classes.TestClass;
import net.meetwave.meetwaverest.Classes.TestClassRequest;
import org.bukkit.plugin.java.JavaPlugin;
import spark.Spark;

import java.sql.*;

import static spark.Spark.*;

public final class MeetWave_REST extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("REST-API ELINDULT");
        Spark.port(10046);
        Spark.secure("./letsencrypt.jks", "valamijelszoxdxd1234912", null, null);
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));


        // EZ A SOR FÖLÖTTI KÓDHOZ NEM KELL NYÚLNI

        // Adatbázis kapcsolat konfigurálása
        String dbUrl = "jdbc:mysql://sql.tokyohost.eu:3306/s372_meetwave";
        String username = "u372_fFBbfDI6rq";
        String password = "yak66HlZx@pah3A8862=2gj.";

        // típusok -> get / post
        // get kérés -> lekérsz valamit (pl.: user adatainak lekérése)
        // post -> valamit küldesz (pl.: regisztráció)
        get("/test", (req, res) -> {
            // path -> mi legyen az endpoint neve
            // jelenleg: ipcím+port + "/test"
            Gson gson = new Gson();
            // bemenő paraméterek kikérése
            TestClassRequest request = gson.fromJson(req.body(), TestClassRequest.class);
            String firstname = request.getFirstname();
            String lastname = request.getLastname();
            int age = request.getAge();

            // adatbázis művetelet
            try {
                // Adatbázis kapcsolat létrehozása
                Connection conn = DriverManager.getConnection(dbUrl, username, password);
                // Tárolt meghívása
                // "getUsers()" helyére megy az adatbázisban létrehozott tárolt neve, és
                // zárójelek a végére, mert  egy függvény
                String storedProcedureCall = "{CALL getUsers()}";
                try (PreparedStatement stmt = conn.prepareCall(storedProcedureCall)) {
                    // Tárolt futtatása és visszatérő érték kiszedése
                    ResultSet resultSet = stmt.executeQuery();
                    // Ha van visszatérő érték (mindig lesz), akkor csináljunk vele valamit
                    while (resultSet.next()) {
                        // pl. változóban tárolás, későbbi feldolgozás
                        // resultSet változóban van az összes válasz a tárolttól
                        // abból kell kiszedni az értékeket a resultSet.(valami)-vel
                        // (valami) lehet : getString, getArray, getInt, getBlob, etc.
                        String email = resultSet.getString("email");
                        // ha a tárolt visszaad egy email-t a válaszban akkor ezzel kiszedi és tárolja az email változóban
                        getServer().getConsoleSender().sendMessage("megjött a tárolt válasz, email értéke: " + email);
                    }
                }
                // Kapcsolat lezárása, FONTOS!!
                conn.close();
            } catch (SQLException e) {
                // ez pedig csak van ha lenne valami error
                e.printStackTrace();
            }

            // visszatérő értékek létrehozása
            TestClass test = new TestClass(firstname, lastname, age);

            // érték visszaadása
            return gson.toJson(test);
        });


        












    }


    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("REST-API LEÁLLT");
    }
}
