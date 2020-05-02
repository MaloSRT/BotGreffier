package fr.msrt.botgreffier.json;

import org.json.JSONObject;

public class JSONObjectHandler extends JSONObject {

    private static JSONObjectHandler instance;

    public JSONObjectHandler(String name) {
        super(new Loader().load(name));
        instance = this;
    }

    public static JSONObjectHandler getInstance() {
        return instance;
    }

    public static int getIntValue(String key) {
        return instance.getInt(key);
    }

    public static String getStringValue(String key) {
        return instance.getString(key);
    }

}