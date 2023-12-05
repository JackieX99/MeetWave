package net.meetwave.meetwaverest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.meetwave.meetwaverest.Classes.*;
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

            // adatbázis művetelek
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
            RegisterResponse resp = new RegisterResponse("unknownerror");
            ;
            if (userExists) {
                resp = new RegisterResponse("emailinuse");
            }
            // még nincs user, mehet a reg
            else {
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
                if (success == 1) {
                    resp = new RegisterResponse("success");
                }
            }

            callableStatement.close();
            connection.close();
            return gson.toJson(resp);
        });

        post("/login", (req, res) -> {
            Gson gson = new Gson();

            LoginClass request = gson.fromJson(req.body(), LoginClass.class);
            // bejövő adatok
            String emailIN = request.getEmail();
            String passwordIN = request.getPassword();

            // db kapcsolat, létező account check
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);


            String storedProcedureCall = "{CALL loginUser(?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);


            callableStatement.setString(1, emailIN);
            callableStatement.setString(2, passwordIN);


            callableStatement.registerOutParameter(3, Types.VARCHAR);


            callableStatement.execute();

            // Elérjük az "OUT" irányú paramétert
            String status = callableStatement.getString(3);

            LoginResponse resp;

            switch (status) {
                case "successful":
                    resp = new LoginResponse("success");
                    break;
                case "wrongpassword":
                    resp = new LoginResponse("wrongpassword");
                    break;
                case "emailnotfound":
                    resp = new LoginResponse("emailnotfound");
                    break;
                default:
                    resp = new LoginResponse("unknownerror");
            }

            callableStatement.close();
            connection.close();
            return gson.toJson(resp);
        });

        post("/changePassword", (req, res) -> {
            Gson gson = new Gson();

            changePasswordClass request = gson.fromJson(req.body(), changePasswordClass.class);
            // bejövő adatok
            int userID = Integer.parseInt(request.getUserID());
            String newpassword = request.getNewpassword();

            // db kapcsolat, létező account check
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);


            String storedProcedureCall = "{CALL changePassword(?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);

            callableStatement.setInt(1, userID);
            callableStatement.setString(2, newpassword);
            callableStatement.registerOutParameter(3, Types.BOOLEAN);

            callableStatement.execute();

            boolean status = callableStatement.getBoolean(3);

            LoginResponse resp;

            if (status) {
                resp = new LoginResponse("success");
            } else {
                resp = new LoginResponse("failed");
            }

            callableStatement.close();
            connection.close();
            return gson.toJson(resp);


        });


        post("/updateUser", (req, res) -> {
            Gson gson = new Gson();

            updateUserClass request = gson.fromJson(req.body(), updateUserClass.class);
            // bejövő adatok
            int userID = request.getUserID();
            String newFullName = request.getNewFullName();
            String newEmail = request.getNewEmail();
            String newPhoneNumber = request.getNewPhoneNumber();


            // db kapcsolat, létező account check
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);


            String storedProcedureCall = "{CALL updateUser(?, ?, ?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);

            callableStatement.setInt(1, userID);
            callableStatement.setString(2, newFullName);
            callableStatement.setString(3, newEmail);
            callableStatement.setString(4, newPhoneNumber);

            callableStatement.registerOutParameter(5, Types.VARCHAR);

            callableStatement.execute();

            String result = callableStatement.getString(5);

            LoginResponse resp;

            if (result.equals("successful")) {
                resp = new LoginResponse("success");
            } else {
                resp = new LoginResponse("failed");
            }

            callableStatement.close();
            connection.close();
            return gson.toJson(resp);

        });

        post("/createEvent", (req, res) -> {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

            createEventClass request = gson.fromJson(req.body(), createEventClass.class);

            // bejövő adatok
            String eventTitle = request.getEventTitle();
            String description = request.getDescription();
            java.util.Date dateOfTheEvent = request.getDateOfTheEvent(); // Most már java.util.Date
            String place = request.getPlace();
            String founder = request.getFounder();
            Timestamp dateOfCreatingEvent = request.getDateOfCreatingEvent();
            int maxParticipants = request.getMaxParticipants();

            // db kapcsolat, létező account check
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            String storedProcedureCall = "{CALL createEvent(?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);

            callableStatement.setString(1, eventTitle);
            callableStatement.setString(2, description);
            callableStatement.setTimestamp(3, new java.sql.Timestamp(dateOfTheEvent.getTime()));
            callableStatement.setString(4, place);
            callableStatement.setString(5, founder);
            callableStatement.setTimestamp(6, dateOfCreatingEvent);
            callableStatement.setInt(7, maxParticipants);

            callableStatement.registerOutParameter(8, Types.VARCHAR);

            callableStatement.execute();

            String result = callableStatement.getString(8);

            LoginResponse resp;

            if (result.equals("successful")) {
                resp = new LoginResponse("success");
            } else {
                resp = new LoginResponse("failed");
            }

            callableStatement.close();
            connection.close();
            return gson.toJson(resp);
        });

        post("/deleteEvent", (req, res) -> {
            Gson gson = new Gson();

            deleteEventClass request = gson.fromJson(req.body(), deleteEventClass.class);

            // bejövő adatok
            int eventID = request.getEventID();

            // db kapcsolat, létező account check
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            String storedProcedureCall = "{CALL deleteEvent(?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);

            callableStatement.setInt(1, eventID);
            callableStatement.registerOutParameter(2, Types.VARCHAR);

            callableStatement.execute();

            String result = callableStatement.getString(2);

            LoginResponse resp;

            if (result.equals("successful")) {
                resp = new LoginResponse("success");
            } else {
                resp = new LoginResponse("failed");
            }

            callableStatement.close();
            connection.close();
            return gson.toJson(resp);
        });

        post("/postComment", (req, res) -> {
            Gson gson = new Gson();

            postCommentClass request = gson.fromJson(req.body(), postCommentClass.class);

            // bejövő adatok
            int eventID = request.getEventID();
            int userID = request.getUserID();
            String userCommentIN = request.getUserCommentIN();
            java.util.Date dateOfCommentIN = request.getDateOfCommentIN();

            // db kapcsolat, létező account check
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            String storedProcedureCall = "{CALL postComment(?, ?, ?, ?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);

            callableStatement.setInt(1, eventID);
            callableStatement.setInt(2, userID);
            callableStatement.setString(3, userCommentIN);
            callableStatement.setTimestamp(4, new java.sql.Timestamp(dateOfCommentIN.getTime()));

            callableStatement.registerOutParameter(5, Types.VARCHAR);

            callableStatement.execute();

            String result = callableStatement.getString(5);

            LoginResponse resp;

            if (result.equals("successful")) {
                resp = new LoginResponse("success");
            } else {
                resp = new LoginResponse("failed");
            }

            callableStatement.close();
            connection.close();
            return gson.toJson(resp);
        });

        post("/deleteComment", (req, res) -> {
            Gson gson = new Gson();

            deleteCommentClass request = gson.fromJson(req.body(), deleteCommentClass.class);

            // bejövő adatok
            int commentID = request.getCommentID();

            // db kapcsolat, létező account check
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            String storedProcedureCall = "{CALL deleteComment(?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);

            callableStatement.setInt(1, commentID);
            callableStatement.registerOutParameter(2, Types.VARCHAR);

            callableStatement.execute();

            String result = callableStatement.getString(2);

            LoginResponse resp;

            if (result.equals("successful")) {
                resp = new LoginResponse("success");
            } else {
                resp = new LoginResponse("failed");
            }

            callableStatement.close();
            connection.close();
            return gson.toJson(resp);
        });


        post("/banUser", (req, res) -> {
            Gson gson = new Gson();

            banUserClass request = gson.fromJson(req.body(), banUserClass.class);

            // bejövő adatok
            int userID = request.getUserID();

            // db kapcsolat, létező account check
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            String storedProcedureCall = "{CALL banUser(?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);

            callableStatement.setInt(1, userID);
            callableStatement.registerOutParameter(2, Types.VARCHAR);

            callableStatement.execute();

            String result = callableStatement.getString(2);

            LoginResponse resp;

            if (result.equals("successful")) {
                resp = new LoginResponse("success");
            } else {
                resp = new LoginResponse("failed");
            }

            callableStatement.close();
            connection.close();
            return gson.toJson(resp);
        });


        post("/unBanUser", (req, res) -> {
            Gson gson = new Gson();

            unBanUserClass request = gson.fromJson(req.body(), unBanUserClass.class);

            // bejövő adatok
            int userID = request.getUserID();

            // db kapcsolat, létező account check
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            String storedProcedureCall = "{CALL unBanUser(?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);

            callableStatement.setInt(1, userID);
            callableStatement.registerOutParameter(2, Types.VARCHAR);

            callableStatement.execute();

            String result = callableStatement.getString(2);

            LoginResponse resp;

            if (result.equals("successful")) {
                resp = new LoginResponse("success");
            } else {
                resp = new LoginResponse("failed");
            }

            callableStatement.close();
            connection.close();
            return gson.toJson(resp);
        });

        post("/muteUser", (req, res) -> {
            Gson gson = new Gson();

            muteUserClass request = gson.fromJson(req.body(), muteUserClass.class);

            // bejövő adatok
            int userID = request.getUserID();

            // db kapcsolat, létező account check
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            String storedProcedureCall = "{CALL muteUser(?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);

            callableStatement.setInt(1, userID);
            callableStatement.registerOutParameter(2, Types.VARCHAR);

            callableStatement.execute();

            String result = callableStatement.getString(2);

            LoginResponse resp;

            if (result.equals("successful")) {
                resp = new LoginResponse("success");
            } else {
                resp = new LoginResponse("failed");
            }

            callableStatement.close();
            connection.close();
            return gson.toJson(resp);
        });

        post("/unMuteUser", (req, res) -> {
            Gson gson = new Gson();

            muteUserClass request = gson.fromJson(req.body(), muteUserClass.class);

            // bejövő adatok
            int userID = request.getUserID();

            // db kapcsolat, létező account check
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            String storedProcedureCall = "{CALL unMuteUser(?, ?)}";
            CallableStatement callableStatement = connection.prepareCall(storedProcedureCall);

            callableStatement.setInt(1, userID);
            callableStatement.registerOutParameter(2, Types.VARCHAR);

            callableStatement.execute();

            String result = callableStatement.getString(2);

            LoginResponse resp;

            if (result.equals("successful")) {
                resp = new LoginResponse("success");
            } else {
                resp = new LoginResponse("failed");
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
