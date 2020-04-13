package fr.msrt.botgreffier.music.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.music.MusicPlayer;
import fr.msrt.botgreffier.music.MusicUtils;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Replay extends Command {

    public Replay() {
        this.name = "replay";
        this.aliases = new String[]{"restart", "relecture", "recommencer"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (MusicUtils.canControl(event) && MusicUtils.isActive(event)) {

            MusicPlayer player = Play.manager.getPlayer(event.getGuild());

            event.reply(Constants.EMOTE_REPLAY + " **Relecture du titre en cours**");
            player.replay();

            if (player.isPaused()) {
                player.resumeFromActive();
            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}