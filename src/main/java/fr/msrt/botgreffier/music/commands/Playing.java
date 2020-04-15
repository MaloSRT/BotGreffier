package fr.msrt.botgreffier.music.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.music.MusicUtils;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;

import java.awt.Color;

public class Playing extends Command {

    public Playing() {
        this.name = "playing";
        this.aliases = new String[]{"nowplaying", "np", "encours", "ec"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (!event.getChannelType().isGuild()) {

            event.reply(Constants.ERR_MP);

        } else if (MusicUtils.isActive(event) && MusicUtils.nonNullTrack(event)) {

            AudioTrack track = Play.manager.getPlayer(event.getGuild()).getListener().getPlayingTrack();
            EmbedBuilder embed = new EmbedBuilder();
            boolean playing = !Play.manager.getPlayer(event.getGuild()).isPaused();

            embed.setAuthor(playing ? "\uD83D\uDD0A Lecture en cours" : "\uD83D\uDD07 Lecture en pause")
                    .setTitle(track.getInfo().title, track.getInfo().uri)
                    .setDescription(progressBar(track.getPosition(), track.getDuration(), playing)
                            + "\n\n__Auteur__ : " + track.getInfo().author)
                    .setColor(new Color(67, 181, 129));
            event.reply(embed.build());

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

    private static String progressBar(long current, long total, boolean playing) {

        StringBuilder builder = new StringBuilder("`");
        long progression = (current * 20) / total;

        if (playing) {
            builder.append("▶ ");
        } else {
            builder.append("▌▌");
        }

        for (int i = 0; i < progression; i++) {
            builder.append("—");
        }

        builder.append("◉");

        for (int i = 0; i < 19 - progression; i++) {
            builder.append("—");
        }

        builder.append(" ")
                .append(MusicUtils.parseDuration(current))
                .append(" / ")
                .append(MusicUtils.parseDuration(total));

        return builder.append("`").toString();

    }

}