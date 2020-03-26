package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;

public class LS2k17 extends Command {

    public LS2k17() {
        this.name = "ls2k17";
        this.aliases = new String[]{"lien"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply(Constants.EMOTE_CHAT + " " + Constants.URL_LS2k17);
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}