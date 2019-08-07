package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Telecharger extends Command {

    public Telecharger() {
        this.name = "télécharger";
        this.aliases = new String[]{"telecharger"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply(Constants.EMOTE_CHAT + " " + Constants.URL_LS2k17 + "/telecharger");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
