package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Source extends Command {

    public Source() {
        this.name = "source";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("Code source du bot : " + Constants.URL_GIT);
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }



}
