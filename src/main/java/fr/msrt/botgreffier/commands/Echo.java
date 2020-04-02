package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Echo extends Command {

    public Echo() {
        this.name = "echo";
        this.ownerCommand = true;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (event.getArgs().isEmpty()) {
            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay(), "[texte]"));
        } else {
            event.reply(event.getMessage().getContentDisplay().substring(event.getClient().getPrefix().length() + 5));
        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}