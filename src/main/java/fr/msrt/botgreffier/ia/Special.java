package fr.msrt.botgreffier.ia;

import fr.msrt.botgreffier.ia.specials.Shutterstock;
import fr.msrt.botgreffier.ia.specials.Weather;
import fr.msrt.botgreffier.ia.specials.YouTube;
import net.dv8tion.jda.api.MessageBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Special {

    public static MessageBuilder search(JSONObject response, String message) {

        String args;
        JSONObject special = response.getJSONObject("special");

        if (special.has("singlearg") && special.getBoolean("singlearg")) {
            args = getSearchArg(response, message);
        } else {
            args = getSearchArgs(response, message);
        }

        if (args.isEmpty()) {
            return null;
        } else {
            switch (response.getJSONObject("special").getString("name")) {
                case "shutterstock":
                    return Shutterstock.getMessageBuilder(args);
                case "youtube":
                    return YouTube.getMessageBuilder(args);
                case "weather":
                    return Weather.getMessageBuilder(args);
                default:
                    return null;
            }
        }

    }

    private static String getSearchArg(JSONObject response, String message) {

        String[] msg = message.split("[^A-Za-zÀ-ÖØ-öø-ÿ-]+"); // TODO à vérifier
        return getArgs(response, msg, true);

    }

    private static String getSearchArgs(JSONObject response, String message) {

        String[] msg = message.split("[^A-Za-zÀ-ÖØ-öø-ÿ]+");
        return getArgs(response, msg, false);

    }

    private static String getArgs(JSONObject response, String[] message, boolean singleArg) {

        ArrayList<JSONArray> toIgnore = new ArrayList<>();
        StringBuilder argsBuilder = new StringBuilder();
        JSONObject special = response.getJSONObject("special");
        JSONArray patterns = new JSONArray().put(response.getJSONArray("pattern"));
        toIgnore.add(Objects.requireNonNull(IAUtils.getPattern("notargs")).getJSONArray("words"));
        toIgnore.add(Objects.requireNonNull(IAUtils.getPattern("greffier")).getJSONArray("equals"));

        if (special.has("notargspattern")) {
            patterns.put(special.getJSONArray("notargspattern"));
        }

        for (int i = 0; i < patterns.length(); i++) {
            JSONArray pattern = patterns.getJSONArray(i);
            for (int j = 0; j < pattern.length(); j++) {
                JSONObject p = IAUtils.getPattern(pattern.getString(j));
                if (p != null) {
                    if (p.has("contains")) {
                        toIgnore.add(p.getJSONArray("contains"));
                    }
                    if (p.has("words")) {
                        toIgnore.add(p.getJSONArray("words"));
                    }
                }
            }
        }

        ArrayList<String> ignoredWords = getIgnoredWords(toIgnore);

        for (String patternElt: ignoredWords) {
            for (int i = 0; i < message.length; i++) {
                if (patternElt.equals(message[i])) {
                    message[i] = null;
                }
            }
        }

        for (String word: message) {
            if (word != null) {
                if (singleArg) {
                    return word;
                } else {
                    argsBuilder.append(word).append(" ");
                }
            }
        }

        return argsBuilder.toString();

    }

    private static ArrayList<String> getIgnoredWords(ArrayList<JSONArray> patterns) {

        ArrayList<String> ignoredWords = new ArrayList<>();

        for (JSONArray pattern: patterns) {
            for (int i = 0; i < pattern.length(); i++) {
                ignoredWords.add(pattern.getString(i));
            }
        }

        return ignoredWords;

    }

}