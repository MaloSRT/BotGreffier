package fr.msrt.botgreffier.ia;

import fr.msrt.botgreffier.json.Loader;
import org.json.JSONArray;
import org.json.JSONObject;

public class IAResponses extends JSONObject {

    private static IAResponses instance;

    public IAResponses(String name) {
        super(new Loader().load(name));
        if (isValid(this)) {
            instance = this;
            System.out.println("[IA] " + name + ": OK");
        } else {
            throw new IllegalArgumentException(name + " is invalid");
        }
    }

    public static IAResponses getInstance() {
        return instance;
    }

    private static boolean isValid(IAResponses instance) {
        if (instance.has("patterns") || !instance.has("responses")) {
            JSONArray patterns = instance.getJSONArray("patterns");
            JSONArray responses = instance.getJSONArray("responses");
            for (int i = 0; i < patterns.length(); i++) {
                if (!patterns.getJSONObject(0).has("name")) {
                    return false;
                }
            }
            for (int i = 0; i < responses.length(); i++) {
                JSONObject response = responses.getJSONObject(i);
                if (!response.has("pattern") || !response.has("ans") || !response.has("priority")) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}