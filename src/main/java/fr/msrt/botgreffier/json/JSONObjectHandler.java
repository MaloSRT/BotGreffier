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

    public static int getIntKey(String name) {
        return instance.getInt(name);
    }

    public static String getStringKey(String name) {
        return instance.getString(name);
    }

}