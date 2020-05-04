package fr.msrt.botgreffier.ia.specials;

import fr.msrt.botgreffier.features.YTSearch;
import net.dv8tion.jda.api.MessageBuilder;

public class YouTube {

    public static MessageBuilder getMessageBuilder(String args) {
        String ytResult = new YTSearch().getYTSearch(args);
        if (ytResult == null) {
            return null;
        } else {
            return new MessageBuilder().append(ytResult);
        }
    }

}