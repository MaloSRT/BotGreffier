package fr.msrt.botgreffier.ia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class IAv2 {

    public static String getAnswer(String message) {

        ArrayList<String> pattern = getPattern(message.toLowerCase());

        if (!pattern.isEmpty()) {
            JSONObject response = getResponse(pattern);
            if (response != null && !response.isEmpty()) {
                return AnswerBuilder.build(response);
            }
        }

        return null;

    }

    private static ArrayList<String> getPattern(String message) {

        JSONArray patterns = IAResponses.getInstance().getJSONArray("patterns");
        ArrayList<String> pattern = new ArrayList<>();

        for (int i = 0; i < patterns.length(); i++) {
            JSONObject jsonPattern = patterns.getJSONObject(i);
            if (hasPattern(message, jsonPattern)) {
                pattern.add(jsonPattern.getString("name"));
            }
        }

        return pattern;

    }

    private static boolean hasPattern(String message, JSONObject jsonPattern) {

        JSONArray jsonContains = jsonPattern.getJSONArray("contains");
        JSONArray jsonWords = jsonPattern.getJSONArray("words");
        String[] contains = new String[jsonContains.length()];

        for (int i = 0; i < jsonContains.length(); i++) {
            contains[i] = jsonContains.getString(i);
        }

        if (Stream.of(contains).anyMatch(message::contains)) {
            return true;
        } else {
            String[] msgWords = message.split(" ");
            for (int i = 0; i < jsonWords.length(); i++) {
                if (Stream.of(msgWords).anyMatch(jsonWords.getString(i)::contains)) {
                    return true;
                }
            }
        }

        return false;

    }

    private static JSONObject getResponse(ArrayList<String> pattern) {

        ArrayList<JSONObject> responses = new ArrayList<>();
        ArrayList<JSONObject> prioResponses = new ArrayList<>();
        ArrayList<JSONObject> matchingResponses = getResponses(pattern);
        int priority = 0;
        int nbPatternElts = 0;


        for (JSONObject response: matchingResponses) {
            int prio = response.getInt("priority");
            if (prio == priority) {
                prioResponses.add(response);
            } else if (prio > priority) {
                prioResponses.clear();
                prioResponses.add(response);
                priority = prio;
            }
        }

        for (JSONObject response: prioResponses) {
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

    private static ArrayList<JSONObject> getResponses(ArrayList<String> mPattern) {

        ArrayList<JSONObject> responses = new ArrayList<>();
        JSONArray allResponses = IAResponses.getInstance().getJSONArray("responses");

        for (int i = 0; i < allResponses.length(); i++) {

            JSONObject response = allResponses.getJSONObject(i);
            JSONArray rPattern = response.getJSONArray("pattern");

            if (matchPattern(rPattern, mPattern)) {
                responses.add(response);
            }

        }

        return responses;

    }

    private static boolean matchPattern(JSONArray rPattern, ArrayList<String> mPattern) {

        for (int i = 0; i < rPattern.length(); i++) {
            if (!hasPatternElt(rPattern.getString(i), mPattern)) {
                return false;
            }
        }
        return true;

    }

    private static boolean hasPatternElt(String rPatternElt, ArrayList<String> mPattern) {

        for (String mPatternElt: mPattern) {
            if (rPatternElt.equals(mPatternElt)) {
                return true;
            }
        }
        return false;

    }

}