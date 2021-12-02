package fr.msrt.botgreffier.music;

import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.music.commands.Play;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MusicUtils {

    public static String formatDuration(Long duration) {

        long hours = TimeUnit.MILLISECONDS.toHours(duration);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
                - TimeUnit.HOURS.toMinutes(hours);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
                - TimeUnit.HOURS.toSeconds(hours)
                - TimeUnit.MINUTES.toSeconds(minutes);

        if (duration < 3600000) {
            return String.format("%02d:%02d", minutes, seconds);
        } else {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }

    }

    public static boolean notTooLong(Long duration) {
        return duration <= 3660000; // 1 heure et 1 minute
    }

    public static boolean canControl(CommandEvent event) {

        if (!event.getChannelType().isGuild()) {
            event.reply(Constants.ERR_MP);
            return false;
        }

        Guild guild = event.getGuild();
        VoiceChannel voiceChannel = Objects.requireNonNull(Objects.requireNonNull(guild.getMember(event.getAuthor())).getVoiceState()).getChannel();

        if (guild.getAudioManager().isConnected() && !Objects.equals(guild.getAudioManager().getConnectedChannel(), voiceChannel)) {
            event.reply(Constants.ERR_SAME_VOC);
            return false;
        } else if (voiceChannel == null) {
            event.reply(Constants.ERR_VOC);
            return false;
        } else {
            return true;
        }

    }

    public static boolean isActive(CommandEvent event) {

        Guild guild = event.getGuild();

        if (!guild.getAudioManager().isConnected()) {
            event.reply(Constants.ERR_NO_TRACK);
            return false;
        } else {
            return true;
        }

    }

    public static boolean nonNullTrack(Guild guild) {
        try {
            Play.getManager().getPlayer(guild).getListener().getPlayingTrack().getInfo();
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static boolean nonNullTrack(CommandEvent event) {
        if (nonNullTrack(event.getGuild())) {
            return true;
        } else {
            event.reply(Constants.ERR_NO_TRACK);
            return false;
        }
    }

}