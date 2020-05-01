package fr.msrt.botgreffier.ia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.stream.Stream;

public class IAv2 {

    public static String getAnswer(String message) {




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

}