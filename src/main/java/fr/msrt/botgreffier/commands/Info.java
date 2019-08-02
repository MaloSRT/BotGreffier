package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.api.Permission;

import java.awt.*;

public class Info extends Command {

    public Info() {
        this.name = "info";
        this.aliases = new String[]{"infos"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor("Informations sur le bot",
                null,
                "https://cdn.discordapp.com/attachments/450705881244499979/594658144710557696/i.png")
                .setThumbnail("https://cdn.discordapp.com/avatars/449218073216679936/f33c0b828798447d08d01c2276d6f830.png?size=256")
                .addField("Nom de l'instance", fr.msrt.botgreffier.info.Info.getInstanceName(), false)
                .addField("Version du bot", fr.msrt.botgreffier.info.Info.getVer(), false)
                .addField("Dernière mise à jour", fr.msrt.botgreffier.info.Info.getMaj(), false)
                .addField("Version Java", java.lang.System.getProperty("java.version"), true)
                .addField("Version JDA", JDAInfo.VERSION, true)
                .addField("OS", java.lang.System.getProperty("os.name")
                        + " (" + java.lang.System.getProperty("os.arch") + ")", true)
                .addField("Version de l'OS", java.lang.System.getProperty("os.version"), true)
                .setColor(Color.CYAN);
        event.reply(embed.build());

        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
