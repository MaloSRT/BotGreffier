package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.utils.CmdUtils;

import java.time.temporal.ChronoUnit;

public class Ping extends Command {

    public Ping() {
        this.name = "ping";
        this.aliases = new String[]{"pong"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        event.reply(":ping_pong: Ping ...", m -> {
            long ping = event.getMessage().getTimeCreated().until(m.getTimeCreated(), ChronoUnit.MILLIS);
            m.editMessage(":ping_pong: Ping : `" + ping  + "ms` | Gateway : `" + event.getJDA().getGatewayPing() + "ms`").queue();
        });
        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}