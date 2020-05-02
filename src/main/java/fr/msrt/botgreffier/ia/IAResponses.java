package fr.msrt.botgreffier.ia;

import fr.msrt.botgreffier.json.Loader;
import org.json.JSONObject;

public class IAResponses extends JSONObject {

    private static IAResponses instance;

    public IAResponses(String name) {
        super(new Loader().load(name));
        instance = this;
    }

    public static IAResponses getInstance() {
        return instance;
    }

}