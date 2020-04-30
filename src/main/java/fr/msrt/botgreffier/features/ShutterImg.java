package fr.msrt.botgreffier.features;

import fr.msrt.botgreffier.config.Config;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class ShutterImg {

    private static HttpURLConnection connection;
    private static final int MAX_RESULTS = 20;

    public static String[] getImage(String query, boolean accurate) {

        String line;
        String details;
        StringBuilder response = new StringBuilder();
        BufferedReader reader;

        if (accurate) {
            details = "&language=fr&image_type=photo";
        } else {
            details = "";
        }

        try {

            URL url = new URL("https://api.shutterstock.com/v2/images/search?query=" + query + "&sort=popular&page=1&per_page=" + MAX_RESULTS + "&safe=false" + details);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(500);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("Authorization", "Bearer " + Config.getStringKey("shutterstock_token"));

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            return parseJSON(response.toString());

        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return null;

    }

    private static String[] parseJSON(String json) {

        /*
         *  imageData[0]: url de l'image à afficher, null si aucun résultat
         *  imageData[1]: url de l'image en grand format
         *  imageData[2]: description de l'image
         */

        String[] imageData = new String[3];
        JSONObject object = new JSONObject(json);

        if (object.has("data") && object.getJSONArray("data").length() > 0) {
            int nbResults = Math.min(object.getInt("total_count"), MAX_RESULTS);
            JSONObject obj = object.getJSONArray("data").getJSONObject(new Random().nextInt(nbResults));
            imageData[0] = obj.getJSONObject("assets").getJSONObject("preview").getString("url");
            imageData[1] = obj.getJSONObject("assets").getJSONObject("preview_1000").getString("url");
            imageData[2] = obj.getString("description");
        }

        return imageData;

    }

}