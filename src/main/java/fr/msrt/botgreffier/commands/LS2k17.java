package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class LS2k17 extends Command {

    public LS2k17() {
        this.name = "LS2k17";
        this.aliases = new String[]{"lien"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("<:chat:447506024388231197> https://ls2k17.wordpress.com");
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
