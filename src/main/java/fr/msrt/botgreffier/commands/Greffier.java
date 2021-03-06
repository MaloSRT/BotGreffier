package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.jda.JDAManager;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Greffier extends Command {

    private final EventWaiter waiter;

    public Greffier(EventWaiter waiter) {
        this.name = "greffier";
        this.aliases = new String[]{"botgreffier"};
        this.waiter = waiter;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

        event.getChannel().sendMessage("Oui c'est moi <:ouicestmoi:503244521295839244>").complete();
        event.getChannel().sendTyping().complete();
        delay();
        event.getChannel().sendMessage("Je suis " + event.getJDA().getSelfUser().getAsMention() + ", un bot Discord.").complete();
        event.getChannel().sendTyping().complete();
        delay();
        event.reply("Incroyable n'est-ce pas ?", m -> {
            m.addReaction(Objects.requireNonNull(JDAManager.getShardManager().getEmoteById(Constants.EMOTE_ID_OUI))).queue();
            m.addReaction(Objects.requireNonNull(JDAManager.getShardManager().getEmoteById(Constants.EMOTE_ID_NON))).queue();
            waiter.waitForEvent(MessageReactionAddEvent.class,
                    e -> e.getMessageId().equals(m.getId()) && event.getAuthor().equals(e.getUser()),
                    e -> {
                       if (e.getReaction().getReactionEmote().getEmote().getId().equals(Constants.EMOTE_ID_OUI)) {
                           event.reply(":D");
                       } else if (e.getReaction().getReactionEmote().getEmote().getId().equals(Constants.EMOTE_ID_NON)) {
                           event.reply(":(");
                       }
                    },
                    30, TimeUnit.SECONDS, () -> event.reply("Pas de réponse, je prends ça pour un oui")
            );
        });

    }

    private static void delay() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}