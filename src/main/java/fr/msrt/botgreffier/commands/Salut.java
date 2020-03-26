package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Salut extends Command {

    public Salut() {
        this.name = "salut";
        this.aliases = new String[]{"slt"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("Salut " + event.getAuthor().getAsMention() + " !");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}