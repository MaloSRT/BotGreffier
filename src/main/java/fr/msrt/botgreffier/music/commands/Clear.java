package fr.msrt.botgreffier.music.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.music.MusicPlayer;
import fr.msrt.botgreffier.music.MusicUtils;
import fr.msrt.botgreffier.utils.CmdUtils;

public class Clear extends Command {

    public Clear() {
        this.name = "clear";
        this.aliases = new String[]{"empty", "vider"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (MusicUtils.canControl(event)) {

            MusicPlayer player = Play.manager.getPlayer(event.getTextChannel().getGuild());

            if (player.getListener().getTracks().isEmpty()) {
                event.reply(Constants.EMOTE_SUCCESS + " **La file d'attente est déjà vide**");
            } else {
                player.getListener().getTracks().clear();
                event.reply(Constants.EMOTE_SUCCESS + " **La file d'attente a été vidée**");
            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

}