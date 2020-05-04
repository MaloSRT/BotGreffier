package fr.msrt.botgreffier.ia;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class AnswerBuilder {

    protected static Message build(JSONObject response, String message) {

        MessageBuilder answer;

        if (response.has("special")) {

            JSONObject special = response.getJSONObject("special");

            if ("search".equals(special.getString("type"))) {
                answer = Special.search(response, message);
            } else {
                answer = new MessageBuilder();
            }

            if (answer != null && response.has("ans")) {
                JSONArray ans = response.getJSONArray("ans");
                answer.getStringBuilder().insert(0, ans.getString(new Random().nextInt(ans.length())));
            }

            if ((answer == null || answer.isEmpty())
                    && special.has("dispnull") && special.getBoolean("dispnull")) {
                answer = new MessageBuilder().append("Je n'ai rien trouvé à ce sujet");
            }

        } else {

            JSONArray answers = response.getJSONArray("ans");
            String ans = answers.get(new Random().nextInt(answers.length())).toString();
            answer = new MessageBuilder();

            if (ans.startsWith("^punct")) {
                answer.append(ans.substring(6));
            } else {
                answer.append(ans);
                if (response.has("punct")) {
                    answer.append(getPunctuation(response.getString("punct")));
                }
            }

        }

        if (answer == null) return null;
        return answer.build();

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