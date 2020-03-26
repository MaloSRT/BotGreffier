package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Oeuf extends Command {

    public Oeuf() {
        this.name = "œuf";
        this.aliases = new String[]{"oeuf", "egg"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        // DISCLAIMER: Hardcore algorithm happening here
        event.reply(":egg:");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}