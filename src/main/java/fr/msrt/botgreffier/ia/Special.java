package fr.msrt.botgreffier.ia;

import fr.msrt.botgreffier.ia.specials.Shutterstock;
import fr.msrt.botgreffier.ia.specials.Weather;
import fr.msrt.botgreffier.ia.specials.YouTube;
import fr.msrt.botgreffier.utils.StringUtils;
import net.dv8tion.jda.api.MessageBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Special {

    /**
     * Retourne le {@link MessageBuilder} construit à partir d'un réponse
     * de type "search" et d'un message.
     *
     * @param response Réponse au format JSON
     * @param message Message
     * @return MessageBuilder
     */
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

    /**
     * Donne l'argument composé d'un seul mot.
     *
     * @param response Réponse au format JSON
     * @param message Message
     * @return L'argument de la recherche
     */
    private static String getSearchArg(JSONObject response, String message) {

        String[] msg = message.split("[^A-Za-zÀ-ÖØ-öø-ÿ0-9-]+");
        return getArgs(response, msg, true);

    }

    /**
     * Donne l'argument qui peut se composer de plusieurs mots.
     *
     * @param response Réponse au format JSON
     * @param message Message
     * @return L'argument de la recherche
     */
    private static String getSearchArgs(JSONObject response, String message) {

        String[] msg = message.split("[^A-Za-zÀ-ÖØ-öø-ÿ0-9]+");
        return getArgs(response, msg, false);

    }

    /**
     * Cherche les arguments dans un message à partir des patterns donnés dans la réponse.
     * Mettre {@code singleArg} à {@code true} pour ne rechercher qu'un seul argument.
     *
     * @param response Réponse au format JSON
     * @param message Message
     * @param singleArg Ne rechercher qu'un seul argument
     * @return Le ou les argument(s), ou {@code null} si aucun n'est trouvé
     */
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

        for (String patternElt : ignoredWords) {
            for (int i = 0; i < message.length; i++) {
                if (message[i] != null &&
                        patternElt.equals(StringUtils.onlyAlphabetLetters(message[i]))) {
                    message[i] = null;
                }
            }
        }

        for (String word : message) {
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

    /**
     * Donne la liste des mots à ignorer dans la recherche d'argument à partir
     * d'une liste de patterns.
     *
     * @param patterns Liste des patterns
     * @return La liste  des mots à ignorer
     */
    private static ArrayList<String> getIgnoredWords(ArrayList<JSONArray> patterns) {

        ArrayList<String> ignoredWords = new ArrayList<>();

        for (JSONArray pattern : patterns) {
            for (int i = 0; i < pattern.length(); i++) {
                ignoredWords.add(pattern.getString(i));
            }
        }

        return ignoredWords;

    }

}