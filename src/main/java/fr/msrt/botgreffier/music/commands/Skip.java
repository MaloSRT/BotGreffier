package fr.msrt.botgreffier.music.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.music.MusicPlayer;
import fr.msrt.botgreffier.music.MusicUtils;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Skip extends Command {

    public Skip() {
        this.name = "skip";
        this.aliases = new String[]{"s", "next", "passer", "sauter"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (MusicUtils.canControl(event) && MusicUtils.isActive(event)) {

            MusicPlayer player = Play.manager.getPlayer(event.getGuild());

            event.reply(Constants.EMOTE_SKIP + " **Piste passée**");
            player.skipTrack();

            if (player.isPaused()) {
                player.resumeFromActive();
            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}