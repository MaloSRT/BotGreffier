package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Telecharger extends Command {

    public Telecharger() {
        this.name = "télécharger";
        this.aliases = new String[]{"telecharger"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("<:chat:447506024388231197> https://ls2k17.wordpress.com/telecharger");
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
