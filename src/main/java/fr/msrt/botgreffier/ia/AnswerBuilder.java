package fr.msrt.botgreffier.ia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class AnswerBuilder {

    protected static String build(JSONObject response) {

        JSONArray answers = response.getJSONArray("ans");
        StringBuilder answer = new StringBuilder();
        answer.append(answers.get(new Random().nextInt(answers.length() - 1)));

        if (response.has("punct")) {
            answer.append(getPunctuation(response.getString("punct")));
        }

        return answer.toString();

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