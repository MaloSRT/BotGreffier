package fr.msrt.botgreffier.config;

import fr.msrt.botgreffier.json.JSONReader;
import fr.msrt.botgreffier.json.JSONWriter;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class
Config {

    private final JSONObject object;
    private final File file;

    public Config(String path) throws IOException {

        this.file = new File(path);
        if (file.exists()) {
                this.object = new JSONReader(file).toJSONObject();
        } else {
            object = new JSONObject();
        }

    }

    public String getString(String key, String defaultValue) {
        if (!object.has(key)) {
            object.put(key, defaultValue);
        }
        return object.getString(key);
    }

    public int getInt(String key, int defaultValue) {
        if (!object.has(key)) {
            object.put(key, defaultValue);
        }
        return object.getInt(key);
    }

    public void save() {

        try (JSONWriter jsonWriter = new JSONWriter(file)) {
            jsonWriter.write(this.object);
            jsonWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
