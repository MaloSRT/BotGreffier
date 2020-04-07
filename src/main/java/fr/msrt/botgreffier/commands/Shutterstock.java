package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.features.ShutterImg;
import fr.msrt.botgreffier.utils.CmdUtils;
import fr.msrt.botgreffier.utils.StringUtils;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.Color;

public class Shutterstock extends Command {

    public Shutterstock() {
        this.name = "shutterstock";
        this.aliases = new String[]{"stock"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (event.getArgs().isEmpty()) {

            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay(), "[recherche]"));

        } else {

            String query = StringUtils.formatURLArg(StringUtils.onlyAlphabetLetters(event.getArgs()));

            String[] image = ShutterImg.getImage(query, true);

            if (image == null) {
                event.reply(Constants.ERR_PROG);
            } else if (image[0] == null) {
                image = ShutterImg.getImage(query, false);
                if (image == null) {
                    event.reply(Constants.ERR_PROG);
                } else if (image[0] == null) {
                    event.reply(Constants.EMOTE_DOUBT + " **Aucune image trouvée**");
                } else {
                    event.reply(getEmbed(image).build());
                }
            } else {
                event.reply(getEmbed(image).build());
            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

    public static EmbedBuilder getEmbed(String[] image) {

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor(image[2], image[1], Constants.ICON_SHUTTER)
                .setImage(image[0])
                .setColor(new Color(245, 67, 54));

        return embed;

    }

}