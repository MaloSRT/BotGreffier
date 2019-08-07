package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

public class PileOuFace extends Command {

    public PileOuFace() {
        this.name = "pileouface";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        double nbAlea = Math.random() * 2;
        if (nbAlea > 1) {
            event.reply("Pile !");
        } else {
            event.reply("Face !");
        }
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
