package fr.msrt.botgreffier.music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import fr.msrt.botgreffier.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MusicManager {

    private final AudioPlayerManager manager = new DefaultAudioPlayerManager();
    private final Map<String, MusicPlayer> players = new HashMap<>();
    private final Color color = new Color(182, 182, 67);

    public MusicManager() {
        AudioSourceManagers.registerRemoteSources(manager);
        AudioSourceManagers.registerLocalSource(manager);
    }

    public synchronized MusicPlayer getPlayer(Guild guild) {
        if (!players.containsKey(guild.getId())) {
            players.put(guild.getId(), new MusicPlayer(manager.createPlayer(), guild));
        }
        return players.get(guild.getId());
    }

    public void loadTrack(final String url, final TextChannel textChannel, final VoiceChannel voiceChannel, final Guild guild, final boolean isPlaying) {

        MusicPlayer player = getPlayer(textChannel.getGuild());
        textChannel.getGuild().getAudioManager().setSendingHandler(player.getAudioHandler());

        manager.loadItemOrdered(player, url, new AudioLoadResultHandler() {

            @Override
            public void trackLoaded(AudioTrack track) {
                if (MusicUtils.notTooLong(track.getDuration())) {
                    if (isPlaying) {
                        EmbedBuilder embed = new EmbedBuilder();
                        embed.setAuthor("\uD83C\uDFB5 Piste ajoutée à la file d'attente")
                                .setDescription("[" + track.getInfo().title
                                        + "](" + track.getInfo().uri
                                        + ") | `" + MusicUtils.formatDuration(track.getDuration()) + "`")
                                .setColor(color);
                        textChannel.sendMessage(embed.build()).queue();
                    } else {
                        guild.getAudioManager().openAudioConnection(voiceChannel);
                    }
                    player.playTrack(track);
                } else {
                    textChannel.sendMessage(Constants.EMOTE_ERR
                            + " **Cette piste ne peut pas être ajoutée car elle dure plus d'une heure**")
                            .queue();
                }
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {

                ArrayList<AudioTrack> tracks = new ArrayList<>();
                StringBuilder builder = new StringBuilder();
                EmbedBuilder embed = new EmbedBuilder();
                int size = Math.min(playlist.getTracks().size(), 10);

                for (int i = 0; i < size; i++) {
                    AudioTrack track = playlist.getTracks().get(i);
                    if (MusicUtils.notTooLong(track.getDuration())) {
                        if (builder.length() != 0) {
                            builder.append("\n");
                        }
                        tracks.add(track);
                        builder.append("[").append(track.getInfo().title)
                                .append("](").append(track.getInfo().uri)
                                .append(") | `").append(MusicUtils.formatDuration(track.getDuration())).append("`");
                    }
                }

                if (tracks.size() == 0) {
                    if (size == 1) {
                        textChannel.sendMessage(Constants.EMOTE_ERR
                                + " **Cette playlist ne peut pas être ajoutée car la première piste dure plus d'une heure**")
                                .queue();
                    } else {
                        textChannel.sendMessage(Constants.EMOTE_ERR
                                + " **Cette playlist ne peut pas être ajoutée car les "
                                + size + " premières pistes durent plus d'une heure**")
                                .queue();
                    }
                    return;
                } else if (playlist.getTracks().size() > 10) {
                    if (tracks.size() == 1) {
                        builder.append("\n\n*Seules la première piste a été ajoutée.*");
                    } else {
                        builder.append("\n\n*Seules les ").append(tracks.size()).append(" premières pistes ont été ajoutées.*");
                    }
                }

                if (!isPlaying) {
                    guild.getAudioManager().openAudioConnection(voiceChannel);
                }

                embed.setAuthor("\uD83C\uDFB6 Playlist ajoutée à la file d'attente")
                        .setDescription(builder.toString())
                        .setColor(color);
                textChannel.sendMessage(embed.build()).queue();

                for (AudioTrack track : tracks) {
                    player.playTrack(track);
                }

            }

            @Override
            public void noMatches() {
                textChannel.sendMessage(Constants.ERR_NO_RESULT).queue();
            }

            @Override
            public void loadFailed(FriendlyException exception) {
                textChannel.sendMessage(Constants.EMOTE_ERR + " **Impossible de jouer la piste**\nDétail : `" + exception.getMessage() + "`").queue();
            }

        });

    }

}