package net.meetwave.meetwaverest;

import com.google.gson.Gson;
import net.meetwave.meetwaverest.Classes.RegisterClass;
import net.meetwave.meetwaverest.Classes.RegisterResponse;
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
        String dbUsername = "u372_fFBbfDI6rq";
        String dbPassword = "yak66HlZx@pah3A8862=2gj.";

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
                Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
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

       //{
       //     "username": "JackieX99",
       //         "email": "foldvaria03@gmail.com",
       //       "password": "Teszt123",
       //         "phone": "06708845584"
       // }


        post("/register", (req, res) -> {
            Gson gson = new Gson();

            RegisterClass request = gson.fromJson(req.body(), RegisterClass.class);
            // bejövő adatok
            String fullnameIN = request.getFullname();
            String emailIN = request.getEmail();
            String passwordIN = request.getPassword();
            String phoneIN = request.getPhone();

            // db kapcsolat, létező account check
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Kapcsolódás az adatbázishoz
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // Hívjuk meg a tárolt eljárást
            String storedProcedureCall = "{CALL checkIfUserExists(?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);

            // Beállítjuk a bemeneti paramétereket
            callableStatement.setString(1, emailIN);

            // Regisztráljuk az "OUT" irányú paramétert
            callableStatement.registerOutParameter(2, Types.BOOLEAN);  // Például, boolean típus

            // Futtatjuk a tárolt eljárást
            callableStatement.execute();

            // Elérjük az "OUT" irányú paramétert
            boolean userExists = callableStatement.getBoolean(2);

            // már létező user
            RegisterResponse resp = new RegisterResponse("unknownerror");;
            if(userExists){
                resp = new RegisterResponse("emailinuse");
            }
            // még nincs user, mehet a reg
            else{
                String storedProcedureCall2 = "{CALL registerUser(?, ?, ?, ?, ?)}";
                CallableStatement callableStatement2 = connection.prepareCall(storedProcedureCall2);
                callableStatement2.setString(1, fullnameIN);
                callableStatement2.setString(2, emailIN);
                callableStatement2.setString(3, passwordIN);
                callableStatement2.setString(4, phoneIN);
                callableStatement2.registerOutParameter(5, Types.INTEGER);
                callableStatement2.execute();

                int success = callableStatement2.getInt(5);
                // ha sikeres regisztráció jött vissza, szóval adatok bementek DB-be
                if(success == 1){
                    resp = new RegisterResponse("success");
                }
            }

            callableStatement.close();
            connection.close();
            return gson.toJson(resp);
        });


    }


    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("REST-API LEÁLLT");
    }
}
