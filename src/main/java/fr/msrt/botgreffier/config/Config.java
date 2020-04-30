package fr.msrt.botgreffier.config;

import org.json.JSONObject;

public class Config extends JSONObject {

    private static Config instance;

    public Config(String name) {
        super(new Loader().load(name));
        instance = this;
    }

    public static int getIntKey(String name) {
        return instance.getInt(name);
    }

    public static String getStringKey(String name) {
        return instance.getString(name);
    }

}