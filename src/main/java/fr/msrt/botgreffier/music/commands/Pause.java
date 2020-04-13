package fr.msrt.botgreffier.music.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.music.MusicUtils;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Pause extends Command {

    public Pause() {
        this.name = "pause";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (MusicUtils.canControl(event) && MusicUtils.isActive(event)) {

            if (Play.manager.getPlayer(event.getGuild()).isPaused()) {
                event.reply(Constants.EMOTE_PAUSE + " **La piste est déjà en pause**");
            } else {
                Play.manager.getPlayer(event.getGuild()).pause();
                event.reply(Constants.EMOTE_PAUSE + " **Pause**");
            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}