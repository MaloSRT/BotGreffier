package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.Permission;

public class Echodel extends Command {

    public Echodel() {
        this.name = "echodel";
        this.botPermissions = new Permission[]{Permission.MESSAGE_MANAGE};
        this.ownerCommand = true;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (event.getArgs().isEmpty()) {
            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay() + " [texte]"));
        } else {
            event.getMessage().delete().queue();
            event.reply(event.getMessage().getContentDisplay().substring(event.getClient().getPrefix().length() + 8));
        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }
}
