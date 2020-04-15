package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.features.YTSearch;
import fr.msrt.botgreffier.utils.CmdUtils;

public class YouTube extends Command {

    public YouTube() {
        this.name = "youtube";
        this.aliases = new String[]{"yt"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        String ytResult = new YTSearch().getYTSearch(event.getArgs());

        if (ytResult == null) {
            event.reply(Constants.ERR_NO_RESULT);
        } else {
            event.reply(Constants.EMOTE_YT + " " + ytResult);
        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}