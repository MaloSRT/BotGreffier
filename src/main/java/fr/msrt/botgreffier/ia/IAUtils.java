package fr.msrt.botgreffier.ia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IAUtils {

    /**
     * Donne la liste des {@link JSONObject} les plus récurrent de la liste passée en paramètre.
     * Tous les éléments de la liste retournée sont apparus le même nombre de fois.
     * Cette liste retournée peut ne contenir qu'un seul élément.
     *
     * @param jsonObjects {@link ArrayList} de {@link JSONObject}
     * @return {@link ArrayList} contenant les {@link JSONObject} les plus récurrents.
     */
    public static ArrayList<JSONObject> mostCommonsJO(ArrayList<JSONObject> jsonObjects) {

        HashMap<JSONObject, Integer> counter = new HashMap<>();
        ArrayList<JSONObject> mostCommonsJO = new ArrayList<>();
        int maxCount = 0;

        for (JSONObject jo : jsonObjects) {
            if (!counter.containsKey(jo)) {
                counter.put(jo, 1);
            } else {
                counter.replace(jo, counter.get(jo) + 1);
            }
        }

        for (Map.Entry<JSONObject, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == maxCount) {
                mostCommonsJO.add(entry.getKey());
            } else if (entry.getValue() > maxCount) {
                mostCommonsJO.clear();
                mostCommonsJO.add(entry.getKey());
                maxCount = entry.getValue();
            }
        }

        return mostCommonsJO;

    }

    /**
     * Retourne le pattern dont le nom est passé en paramètre.
     *
     * @param name Nom du pattern
     * @return Le pattern si une correspondance est trouvée, {@code null} sinon
     */
    public static JSONObject getPattern(String name) {

        JSONArray patterns = IAData.getInstance().getJSONArray("patterns");
        for (int i = 0; i < patterns.length(); i++) {
            JSONObject pattern = patterns.getJSONObject(i);
            if (pattern.getString("name").equals(name)) {
                return pattern;
            }
        }
        return null;

    }

}