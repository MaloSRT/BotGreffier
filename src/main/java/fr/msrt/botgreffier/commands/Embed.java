package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.Color;

public class Embed extends Command {

    public Embed() {
        this.name = "embed";
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_MANAGE};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (!event.getChannelType().isGuild()) {
            event.reply(Constants.ERR_MP);
            return;
        }

        if (event.getArgs().isEmpty()) {
            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay() + " [texte]"));
        } else {
            event.getMessage().delete().queue();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle(event.getArgs())
                    .setFooter("Par " + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator())
                    .setTimestamp(event.getMessage().getTimeCreated())
                    .setColor(Color.YELLOW);
            event.reply(embed.build());
        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}