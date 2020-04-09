package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.api.Permission;

import java.awt.Color;

import static java.lang.System.getProperty;

public class Info extends Command {

    public Info() {
        this.name = "info";
        this.aliases = new String[]{"infos", "botinfo", "information", "informations", "about"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("Informations sur le bot",
                Constants.URL_BOT,
                Constants.INFO_ORANGE)
                .setThumbnail(Constants.AVATAR + "?size=256")
                .addField("Nom de l'instance", fr.msrt.botgreffier.info.Info.INSTANCE_NAME, false)
                .addField("Version du bot", fr.msrt.botgreffier.info.Info.VERSION, false)
                .addField("Dernière mise à jour", fr.msrt.botgreffier.info.Info.LAST_UPDATE, true)
                .addField("Présent dans", event.getClient().getTotalGuilds() + " serveurs", true)
                .addField("Version Java", getProperty("java.version"), true)
                .addField("Version JDA", JDAInfo.VERSION, true)
                .addField("OS", getProperty("os.name")
                        + " (" + getProperty("os.arch") + ")", true)
                .addField("Version de l'OS", getProperty("os.version"), true)
                .setColor(Color.CYAN);
        event.reply(embed.build());

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}