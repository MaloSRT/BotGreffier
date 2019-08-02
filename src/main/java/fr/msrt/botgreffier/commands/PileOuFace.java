package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class PileOuFace extends Command {

    public PileOuFace() {
        this.name = "pileOuFace";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        double nbAlea = Math.random() * 2;
        if (nbAlea > 1) {
            event.reply("Pile !");
        } else {
            event.reply("Face !");
        }
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
