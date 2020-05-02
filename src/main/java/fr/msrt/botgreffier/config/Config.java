package fr.msrt.botgreffier.config;

import fr.msrt.botgreffier.json.Loader;
import org.json.JSONObject;

public class Config extends JSONObject {

    private static Config instance;

    public Config(String name) {
        super(new Loader().load(name));
        instance = this;
        System.out.println("[config] " + name + ": OK");
    }

    public static int getIntValue(String key) {
        return instance.getInt(key);
    }

    public static String getStringValue(String key) {
        return instance.getString(key);
    }

}