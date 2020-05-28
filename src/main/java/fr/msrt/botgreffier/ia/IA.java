package fr.msrt.botgreffier.ia;

import fr.msrt.botgreffier.utils.StringUtils;
import net.dv8tion.jda.api.entities.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class IA {

    /**
     * Donne la réponse de l'IA au message passé en paramètre.
     * Si l'IA ne trouve rien à répondre, retourne {@code null}.
     * La réponse n'est pas sous forme de {@code String} mais de {@code net.dv8tion.jda.api.entities.Message}
     * car elle peut contenir un embed.
     *
     * @param message Message
     * @return La réponse de l'IA, ou {@code null}
     */
    public static Message getAnswer(String message) {

        String msg = message.toLowerCase();
        String cleanMsg = getCleanMessage(msg);
        if (cleanMsg.isEmpty()) return null;

        ArrayList<String> patterns = getPattern(cleanMsg);

        if (!patterns.isEmpty()) {
            JSONObject response = getResponse(patterns);
            if (response != null && !response.isEmpty()) {
                return AnswerBuilder.build(response, msg);
            }
        }

        return null;

    }

    /**
     * Donne le message <i>propre</i> :
     * les éléments contenus dans "cleanup" sont enlevés du message,
     * les accentuations sont retirées,
     * les tirets et apostrophes sont ramplacés par des espaces.
     *
     * @param message Message
     * @return Le message propre
     */
    private static String getCleanMessage(String message) {

        JSONArray cleanup = IAData.getInstance().getJSONArray("cleanup");
        String msg = StringUtils.onlyAlphabetLetters(message).replace("-", " ").replace("'", " ");

        for (int i = 0; i < cleanup.length(); i++) {
            msg = msg.replaceAll(cleanup.getString(i), "");
        }

        return msg;

    }

    /**
     * Donne la liste des patterns correspondant au message.
     * La liste retournée contient les noms de tous les patterns
     * qui contiennent au moins une correspondance au message.
     *
     * @param message Message
     * @return La liste des patterns
     */
    private static ArrayList<String> getPattern(String message) {

        JSONArray patterns = IAData.getInstance().getJSONArray("patterns");
        ArrayList<String> pattern = new ArrayList<>();

        for (int i = 0; i < patterns.length(); i++) {
            JSONObject jsonPattern = patterns.getJSONObject(i);
            if (hasPattern(message, jsonPattern)) {
                pattern.add(jsonPattern.getString("name"));
            }
        }

        return pattern;

    }

    /**
     * Détermine si le message contient le pattern.
     *
     * @param message Message
     * @param jsonPattern Pattern au format JSON
     * @return {@code true} si le message contient le pattern
     */
    private static boolean hasPattern(String message, JSONObject jsonPattern) {

        if (jsonPattern.has("ignore") && jsonPattern.getBoolean("ignore")) {
            return false;
        }

        if (jsonPattern.has("equals")) {
            JSONArray jsonEquals = jsonPattern.getJSONArray("equals");
            String[] equals = new String[jsonEquals.length()];
            for (int i = 0; i < jsonEquals.length(); i++) {
                equals[i] = jsonEquals.getString(i);
            }
            if (Arrays.asList(equals).contains(message)) {
                return true;
            }
        }

        if (jsonPattern.has("contains")) {
            JSONArray jsonContains = jsonPattern.getJSONArray("contains");
            String[] contains = new String[jsonContains.length()];
            for (int i = 0; i < jsonContains.length(); i++) {
                contains[i] = jsonContains.getString(i);
            }
            if (Stream.of(contains).anyMatch(message::contains)) {
                return true;
            }
        }

        if (jsonPattern.has("words")) {
            JSONArray jsonWords = jsonPattern.getJSONArray("words");
            String[] msgWords = message.split("[^A-Za-zÀ-ÖØ-öø-ÿ]+");
            for (int i = 0; i < jsonWords.length(); i++) {
                if (Arrays.asList(msgWords).contains(jsonWords.getString(i))) {
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * Retourne une réponse au format JSON correspondante à la liste de patterns.
     *
     * @param patterns Liste des noms des patterns
     * @return La réponse correspondante patterns.
     */
    private static JSONObject getResponse(ArrayList<String> patterns) {

        ArrayList<JSONObject> responses = new ArrayList<>();
        ArrayList<JSONObject> prioResponses = new ArrayList<>();
        ArrayList<JSONObject> matchingResponses = getResponses(patterns);
        int priority = 0;
        int nbPatternElts = 0;


        for (JSONObject response : matchingResponses) {
            int prio = response.getInt("priority");
            if (prio == priority) {
                prioResponses.add(response);
            } else if (prio > priority) {
                prioResponses.clear();
                prioResponses.add(response);
                priority = prio;
            }
        }

        for (JSONObject response : prioResponses) {
            int nbPE = response.getJSONArray("pattern").length();
            if (nbPE == nbPatternElts) {
                responses.add(response);
            } else if (nbPE > nbPatternElts) {
                responses.clear();
                responses.add(response);
                nbPatternElts = nbPE;
            }
        }

        if (responses.isEmpty()) return null;
        ArrayList<JSONObject> r = IAUtils.mostCommonsJO(responses);
        return r.get(new Random().nextInt(r.size()));

    }

    /**
     * Donne la liste des réponses correspondantes aux patterns.
     * Les réponses sont au format JSON.
     *
     * @param mPatterns Liste des noms des patterns contenu dans le message
     * @return La liste des réponses correspondantes
     */
    private static ArrayList<JSONObject> getResponses(ArrayList<String> mPatterns) {

        ArrayList<JSONObject> responses = new ArrayList<>();
        JSONArray allResponses = IAData.getInstance().getJSONArray("responses");

        for (int i = 0; i < allResponses.length(); i++) {

            JSONObject response = allResponses.getJSONObject(i);
            JSONArray rPattern = response.getJSONArray("pattern");

            if (matchPattern(rPattern, mPatterns)) {
                responses.add(response);
            }

        }

        return responses;

    }

    /**
     * Détermine si la liste des noms de patterns du message et la liste des
     * noms de patterns de la réponse ont au moins un élément en commun.
     *
     * @param rPatterns Liste du nom des patterns de la réponse au format JSON
     * @param mPatterns Liste du nom des patterns du message
     * @return {@code true} si les 2 listes ont un pattern en commun
     */
    private static boolean matchPattern(JSONArray rPatterns, ArrayList<String> mPatterns) {

        for (int i = 0; i < rPatterns.length(); i++) {
            if (!hasPatternElt(rPatterns.getString(i), mPatterns)) {
                return false;
            }
        }
        return true;

    }

    /**
     * Détermine si le nom de pattern est contenu dans la liste de noms de patterns.
     *
     * @param rPattern Nom du pattern
     * @param mPatterns Liste de noms de patterns.
     * @return {@code true} si le nom de pattern est présent dans la liste
     */
    private static boolean hasPatternElt(String rPattern, ArrayList<String> mPatterns) {

        for (String mPattern: mPatterns) {
            if (rPattern.equals(mPattern)) {
                return true;
            }
        }
        return false;

    }

}