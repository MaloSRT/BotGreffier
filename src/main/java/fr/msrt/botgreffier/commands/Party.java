package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;
import fr.msrt.botgreffier.utils.Utils;
import net.dv8tion.jda.api.Permission;

public class Party extends Command {

    public Party() {
        this.name = "party";
        this.aliases = new String[]{"partie"};
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
            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay() + " [jeu], [lien]"));
        } else {
            if (Utils.antiPing(event.getMessage().getContentDisplay())) {
                String[] party = event.getArgs().split(", ");
                if (party.length == 2 && event.getArgs().length() >= 14) {
                    String game = party[0];
                    String link = party[1];
                    if (Utils.isLink(link)) {
                        event.getMessage().delete().queue();
                        event.reply("Une partie de **" + game + "** a commencé ! \nCliquez ici pour la rejoindre : " + link);
                    } else {
                        event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay().split(" ", 2)[0] + " [jeu], [lien]"));
                    }
                } else {
                    event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay().split(" ", 2)[0] + " [jeu], [lien]"));
                }
            } else {
                event.reply(Constants.ERR_PING);
            }
        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
