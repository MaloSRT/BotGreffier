package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.SysoutCmd;

public class PingMoi extends Command {

    public PingMoi() {
        this.name = "pingMoi";
        this.aliases = new String[]{"pingMe"};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply("D'accord");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i <= 24; i++) {
            event.reply(" " + event.getAuthor().getAsMention());
        }
        SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

    }

}
