package fr.msrt.botgreffier.ia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

public class Special {

    public static String search(JSONObject response, String message) {

        String args = getSearchArgs(response.getJSONArray("pattern"), message);

        if (args.isEmpty()) {
            return null;
        } else {
            switch (response.getJSONObject("special").getString("name")) {
                case "shutterstock":
                    // TODO
                case "youtube":
                    // TODO
                default:
                    return null;
            }
        }

    }

    private static String getSearchArgs(JSONArray pattern, String message) {

        JSONArray prepositions = Objects.requireNonNull(IAUtils.getPattern("prepositions")).getJSONArray("words");
        String[] msg = message.split("[^A-Za-zÀ-ÖØ-öø-ÿ]+");
        StringBuilder argsBuilder = new StringBuilder();

        for (int i = 0; i < pattern.length(); i++) {
            JSONObject p = IAUtils.getPattern(pattern.getString(i));
            if (p != null) {
                if (p.has("contains")) {
                    JSONArray contains = p.getJSONArray("contains");
                    for (int j = 0; j < contains.length(); j++) {
                        for (int k = 0; k < msg.length; k++) {
                            if (contains.getString(j).equals(msg[k])) {
                                msg[k] = null;
                            }
                        }
                    }
                } else if (p.has("words")) {
                    JSONArray words = p.getJSONArray("words");
                    for (int j = 0; j < words.length(); j++) {
                        for (int k = 0; k < msg.length; k++) {
                            if (words.getString(j).equals(msg[k])) {
                                msg[k] = null;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < prepositions.length(); i++) {
            for (int j = 0; j < msg.length; j++) {
                if (prepositions.getString(i).equals(msg[j])) {
                    msg[j] = null;
                }
            }
        }

        for (String word: msg) {
            if (word != null) {
                argsBuilder.append(word).append(" ");
            }
        }

        return argsBuilder.toString();

    }

}