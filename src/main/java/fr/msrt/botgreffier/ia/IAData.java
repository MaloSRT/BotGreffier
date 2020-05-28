package fr.msrt.botgreffier.ia;

import fr.msrt.botgreffier.json.Loader;
import org.json.JSONArray;
import org.json.JSONObject;

public class IAData extends JSONObject {

    private static IAData instance;

    public IAData(String name) {
        super(new Loader().load(name));
        if (isValid(this)) {
            instance = this;
            System.out.println("[IA] " + name + ": OK");
        } else {
            throw new IllegalArgumentException(name + " is invalid");
        }
    }

    public static IAData getInstance() {
        return instance;
    }

    private static boolean isValid(IAData instance) {

        if (instance.has("cleanup") && instance.has("patterns") && instance.has("responses")) {
            JSONArray patterns = instance.getJSONArray("patterns");
            JSONArray responses = instance.getJSONArray("responses");
            for (int i = 0; i < patterns.length(); i++) {
                if (!patterns.getJSONObject(0).has("name")) {
                    return false;
                }
            }
            for (int i = 0; i < responses.length(); i++) {
                JSONObject response = responses.getJSONObject(i);
                if (!response.has("pattern")
                        || !(response.has("ans") || response.has("special"))
                        || !response.has("priority")) {
                    return false;
                }
                if (response.has("special")) {
                    JSONObject special = response.getJSONObject("special");
                    if (!special.has("name") || !special.has("type")) {
                        return false;
                    }
                }
            }
            return true;
        }

        return false;

    }

}