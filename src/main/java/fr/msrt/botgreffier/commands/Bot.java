package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Bot extends Command {

    public Bot() {
        this.name = "bot";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply(Constants.EMOTE_BOT);
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}