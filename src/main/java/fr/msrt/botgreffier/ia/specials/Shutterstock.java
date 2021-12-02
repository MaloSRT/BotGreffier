package fr.msrt.botgreffier.ia.specials;

import fr.msrt.botgreffier.features.ShutterImg;
import net.dv8tion.jda.api.MessageBuilder;

public class Shutterstock {

    public static MessageBuilder getMessageBuilder(String args) {
        String[] image = ShutterImg.getImage(args, true);
        if (image == null || image[0] == null) {
            return null;
        } else {
            return new MessageBuilder().setEmbeds(ShutterImg.getEmbed(image).build());
        }
    }

}