package fr.msrt.botgreffier.music;

import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MusicUtils {

    public static String parseDuration(Long duration) {

        if (duration < 3600000) {
            return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
        } else {
            return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(duration),
                TimeUnit.MILLISECONDS.toMinutes(duration) -
                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                    TimeUnit.HOURS.toSeconds(TimeUnit.MILLISECONDS.toHours(duration))
            );
        }

    }

    public static boolean isNotTooLong(Long duration) {
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

        if (!guild.getAudioManager().isConnected() && !guild.getAudioManager().isAttemptingToConnect()) {
            event.reply(Constants.EMOTE_ERR + " **Aucune piste n'est en cours de lecture**");
            return false;
        } else {
            return true;
        }

    }

}