package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class Id extends Command {

    public Id() {
        this.name = "id";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("ID de " + event.getAuthor().getAsMention() + " : `" + event.getAuthor().getId() + "`");
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
