package fr.msrt.botgreffier.ia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class AnswerBuilder {

    protected static String build(JSONObject response, String message) {

        String answer;

        if (response.has("special")) {

            JSONObject special = response.getJSONObject("special");

            if ("search".equals(special.getString("type"))) {
                answer = Special.search(response, message);
            } else {
                answer = null;
            }

        } else {

            JSONArray answers = response.getJSONArray("ans");
            answer = answers.get(new Random().nextInt(answers.length())).toString();

            if (answer.startsWith("^punct")) {
                answer = answer.substring(6);
            } else if (response.has("punct")) {
                answer += getPunctuation(response.getString("punct"));
            }

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

/*

SPECIAL :

    if special.name == "search":
        récupérer le message
        String[] = split mot par mot du message
        retirer du String[] tous les mots du pattern
        retirer du String[] les mots de liaison (sur, de, à, avec)
        si le String[] n'est pas vide





 */