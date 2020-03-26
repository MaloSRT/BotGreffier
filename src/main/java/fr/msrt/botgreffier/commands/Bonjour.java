package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Bonjour extends Command {

    public Bonjour() {
        this.name = "bonjour";
        this.aliases = new String[]{"bjr"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("Bonjour " + event.getAuthor().getAsMention() + " !");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}