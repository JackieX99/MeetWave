package net.meetwave.meetwaverest;

import com.google.gson.Gson;
import net.meetwave.meetwaverest.Classes.TestClass;
import net.meetwave.meetwaverest.Classes.TestClassRequest;
import org.bukkit.plugin.java.JavaPlugin;
import spark.Spark;

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
            //

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
