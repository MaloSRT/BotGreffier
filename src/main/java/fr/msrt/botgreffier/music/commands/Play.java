package fr.msrt.botgreffier.music.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.features.YTSearch;
import fr.msrt.botgreffier.music.MusicManager;
import fr.msrt.botgreffier.music.MusicPlayer;
import fr.msrt.botgreffier.music.MusicUtils;
import fr.msrt.botgreffier.utils.CmdUtils;
import fr.msrt.botgreffier.utils.StringUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.util.Objects;

public class Play extends Command {

    protected static final MusicManager manager = new MusicManager();

    public Play() {
        this.name = "play";
        this.aliases = new String[]{"p", "jouer", "lire"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (MusicUtils.canControl(event)) {

            if (event.getArgs().isEmpty()) {

                resume(event);

            } else {

                Guild guild = event.getGuild();
                VoiceChannel voiceChannel = Objects.requireNonNull(Objects.requireNonNull(guild.getMember(event.getAuthor())).getVoiceState()).getChannel();
                MusicPlayer player = manager.getPlayer(event.getGuild());
                String url;
                player.setTextChannel(event.getTextChannel());

                if (StringUtils.isURL(event.getArgs())) {
                    url = event.getArgs();
                } else {
                    String ytResult = new YTSearch().getYTSearch(event.getArgs());
                    if (ytResult == null) {
                        event.reply(Constants.ERR_NO_RESULT);
                        return;
                    } else {
                        url = ytResult;
                    }
                }

                if (!guild.getAudioManager().isConnected() && !guild.getAudioManager().isAttemptingToConnect()) {
                    manager.loadTrack(url, event.getTextChannel(), voiceChannel, guild, false);
                    if (player.isPaused()) {
                        player.resumeFromActive();
                    }
                } else {
                    manager.loadTrack(url, event.getTextChannel(), null, null, true);
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

    public static MusicManager getManager() {
        return manager;
    }

}