package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;

public class PingMoi extends Command {

    public PingMoi() {
        this.name = "pingmoi";
        this.aliases = new String[]{"pingme"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (!event.getChannelType().isGuild()) {
            event.reply(Constants.ERR_MP);
            return;
        }

        event.reply("D'accord");
        for (int i = 0; i <= 24; i++) {
            try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            event.reply(" " + event.getAuthor().getAsMention());
        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}