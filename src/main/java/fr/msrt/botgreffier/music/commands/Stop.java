package fr.msrt.botgreffier.music.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.music.MusicUtils;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Stop extends Command {

    public Stop() {
        this.name = "stop";
        this.aliases = new String[]{"quit", "leave", "disconnect", "arrêt", "arret", "arrêter", "arreter"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (MusicUtils.canControl(event) && MusicUtils.isActive(event)) {
            Play.manager.getPlayer(event.getGuild()).stop();
            event.reply(Constants.EMOTE_STOP + " **Arrêt de la lecture**");
        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}