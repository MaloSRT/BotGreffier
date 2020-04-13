package fr.msrt.botgreffier.music.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.music.MusicManager;
import fr.msrt.botgreffier.music.MusicPlayer;
import fr.msrt.botgreffier.music.MusicUtils;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.Objects;

public class Play extends Command {

    public Play() {
        this.name = "play";
        this.aliases = new String[]{"p", "jouer", "lire"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = false;
    }

    protected static final MusicManager manager = new MusicManager();

    @Override
    protected void execute(CommandEvent event) {

        if (MusicUtils.canControl(event)) {

            if (event.getArgs().isEmpty()) {

                resume(event);

            } else {

                Guild guild = event.getGuild();
                VoiceChannel voiceChannel = Objects.requireNonNull(Objects.requireNonNull(guild.getMember(event.getAuthor())).getVoiceState()).getChannel();
                MusicPlayer player = manager.getPlayer(event.getGuild());
                player.setTextChannel(event.getTextChannel());

                if (!guild.getAudioManager().isConnected() && !guild.getAudioManager().isAttemptingToConnect()) {
                    guild.getAudioManager().openAudioConnection(voiceChannel);
                    manager.loadTrack(event.getArgs(), event.getTextChannel(), false);
                } else {
                    manager.loadTrack(event.getArgs(), event.getTextChannel(), true);
                }

                if (player.isPaused()) {
                    player.resumeFromActive();
                }

            }

        }

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

    }

    public static void resume(CommandEvent event) {

        Guild guild = event.getGuild();
        MusicPlayer player = manager.getPlayer(guild);

        if (guild.getAudioManager().isConnected() || guild.getAudioManager().isAttemptingToConnect()) {

            if (!player.isPaused()) {
                event.reply(Constants.EMOTE_PLAY + " **La piste est déjà en cours de lecture**");
            } else {
                player.resumeFromActive();
                event.reply(Constants.EMOTE_PLAY + " **Lecture**");
            }

        } else {

            if (player.getListener().getTracks().isEmpty()) {
                event.reply(Constants.EMOTE_DOUBT + " **Il n'y a pas de piste dans la file d'attente**"
                        + "\nEnvoyez `" + CmdUtils.getPrefixUsed(event.getMessage().getContentDisplay()) + "play [piste]` pour en ajouter");
            } else {
                VoiceChannel voiceChannel = Objects.requireNonNull(Objects.requireNonNull(guild.getMember(event.getAuthor())).getVoiceState()).getChannel();
                event.reply(Constants.EMOTE_PLAY + " **Lecture**");
                player.setTextChannel(event.getTextChannel());
                player.resumeFromInactive(voiceChannel);
            }

        }

    }

}