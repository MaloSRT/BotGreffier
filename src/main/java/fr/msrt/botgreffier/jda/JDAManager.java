package fr.msrt.botgreffier.jda;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.examples.command.PingCommand;
import fr.msrt.botgreffier.BotGreffier;
import fr.msrt.botgreffier.commands.*;
import fr.msrt.botgreffier.event.BotListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class JDAManager {

    private static EventWaiter waiter = new EventWaiter();
    private static ShardManager shardManager = buildShard();

    public static ShardManager getShardManager() {
        return shardManager;
    }

    private static ShardManager buildShard() {
        try {
            return new DefaultShardManagerBuilder()
                    .setToken(BotGreffier.CONFIG.getString("token", "TOKEN"))
                    .setShardsTotal(BotGreffier.CONFIG.getInt("shardTotal", 1))
                    .addEventListeners(new BotListener(), getClient().build(), waiter)
                    .setActivity(Activity.playing("se réveiller..."))
                    .setStatus(OnlineStatus.IDLE)
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static CommandClientBuilder getClient() {
        return new CommandClientBuilder()
                .setOwnerId("333329500358180864")
                .setPrefix("=")
                .setActivity(Activity.playing("LS2k17"))
                .useHelpBuilder(false)
                .addCommands(
                        new PingCommand(),
                        new Aide(),
                        new Avatar(),
                        new AVYE(),
                        new Bilboquet(),
                        new Bonjour(),
                        new Bot(),
                        new BotActivity(),
                        new Chapon(),
                        new Chat(),
                        new Chien(),
                        new Echo(),
                        new Echodel(),
                        new Ecris(),
                        new Embed(),
                        new EscapeUnescape(),
                        new Greffier(waiter),
                        new Id(),
                        new Info(),
                        new Instance(),
                        new KillInstance(),
                        new LS2k17(),
                        new Meteo(),
                        new MiseAJour(),
                        new NbChar(),
                        new Oeuf(),
                        new Party(),
                        new Pendu(waiter),
                        new PierreFeuilleCiseaux(),
                        new PileOuFace(),
                        new PingMoi(),
                        new Salut(),
                        new Serveur(),
                        new Snapchat(),
                        new Telecharger(),
                        new Test(),
                        new VadeRetro(),
                        new Version()
                );
    }

}


