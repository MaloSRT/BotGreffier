package fr.msrt.botgreffier.ia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class AnswerBuilder {

    protected static String build(JSONObject response) {

        JSONArray answers = response.getJSONArray("ans");
        String answer = answers.get(new Random().nextInt(answers.length())).toString();

        if (answer.startsWith("^punct")) {
            answer = answer.substring(6);
        } else if (response.has("punct")) {
            answer += getPunctuation(response.getString("punct"));
        }

        return answer;

    }

    private static String getPunctuation(String name) {
        switch (name) {
            case "dot":
                return Punctuation.getRandomDot();
            case "exclamation":
                return Punctuation.getRandomExclamation();
            default:
                return "";
        }
    }

}