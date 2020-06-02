package fr.msrt.botgreffier.ia;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class AnswerBuilder {

    /**
     * Construit une réponse sous forme de message Discord à partir de la réponse en JSON.
     * Peut retourner {@code null}.
     *
     * @param response Réponse au format JSON
     * @param message Message
     * @return Réponse constrite
     */
    protected static Message build(JSONObject response, String message) {

        MessageBuilder answer;

        if (response.has("special")) {
            answer = getSpecial(response, message);
        } else {
            answer = getClassic(response);
        }

        if (answer == null) return null;
        return answer.build();

    }

    /**
     * Retourne le {@link MessageBuilder} construit à partir d'une réponse contenant
     * le {@link JSONObject} "special" et d'un message.
     *
     * @param response Réponse au format JSON
     * @param message Message
     * @return MessageBuilder
     */
    private static MessageBuilder getSpecial(JSONObject response, String message) {

        MessageBuilder answer;
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

        return answer;

    }

    /**
     * Retourne le {@link MessageBuilder} construit à partir d'une réponse classique.
     *
     * @param response Réponse au format JSON
     * @return MessageBuilder
     */
    private static MessageBuilder getClassic(JSONObject response) {

        MessageBuilder answer = new MessageBuilder();
        JSONArray answers = response.getJSONArray("ans");
        String ans = answers.get(new Random().nextInt(answers.length())).toString();

        if (ans.startsWith("^punct")) {
            answer.append(ans.substring(6));
        } else {
            answer.append(ans);
            if (response.has("punct")) {
                answer.append(getPunctuation(response.getString("punct")));
            }
        }

        return answer;

    }

    /**
     * Donne la ponctuation à afficher à la fin du message.
     *
     * @param name Nom de la ponctuation
     * @return La ponctuation
     */
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