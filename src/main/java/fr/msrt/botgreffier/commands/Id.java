package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Id extends Command {

    public Id() {
        this.name = "id";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("ID de " + event.getAuthor().getAsMention() + " : `" + event.getAuthor().getId() + "`");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}