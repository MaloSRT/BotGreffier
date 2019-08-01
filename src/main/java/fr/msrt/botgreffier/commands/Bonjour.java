package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class Bonjour extends Command {

    public Bonjour() {
        this.name = "bonjour";
        this.aliases = new String[]{"bjr"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("Bonjour " + event.getAuthor().getAsMention() + " !");
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }
}
