package fr.msrt.botgreffier.ia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class IAv2 {

    public static String getAnswer(String message) {

        ArrayList<String> pattern = getPattern(message);

        if (!pattern.isEmpty()) {
            // TODO
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

        int priority = 0;
        ArrayList<JSONObject> responses = new ArrayList<>();

        for (String patternElt: pattern) {
            ArrayList<JSONObject> matchingResponses = getResponses(patternElt);
            for (JSONObject response: matchingResponses) {
                int prio = response.getInt("priority");
                if (prio == priority) {
                    responses.add(response);
                } else if (prio > priority) {
                    responses.clear();
                    responses.add(response);
                    priority = prio;
                }
            }
        }

        ArrayList<JSONObject> r = IAUtils.mostCommonsJO(responses);
        return r.get(new Random().nextInt(r.size() - 1));

    }

    private static ArrayList<JSONObject> getResponses(String patternElt) {

        ArrayList<JSONObject> responses = new ArrayList<>();
        JSONArray allResponses = IAResponses.getInstance().getJSONArray("responses");

        for (int i = 0; i < allResponses.length(); i++) {
            JSONObject response = allResponses.getJSONObject(i);
            JSONArray pattern = response.getJSONArray("pattern");
            if (matchPattern(patternElt, pattern)) {
                responses.add(response);
            }
        }

        return responses;

    }

    private static boolean matchPattern(String patternElt, JSONArray pattern) {

        for (int j = 0; j < pattern.length(); j++) {
            if (pattern.getString(j).equals(patternElt)) {
                return true;
            }
        }

        return false;

    }

}