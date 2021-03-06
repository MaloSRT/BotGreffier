package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;

import java.util.Random;

public class PileOuFace extends Command {

    public PileOuFace() {
        this.name = "pileouface";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (new Random().nextBoolean()) {
            event.reply(Constants.EMOTE_COIN + " **Pile !**");
        } else {
            event.reply(Constants.EMOTE_COIN + " **Face !**");
        }
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}