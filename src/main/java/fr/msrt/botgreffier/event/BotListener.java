package fr.msrt.botgreffier.event;

import fr.msrt.botgreffier.ia.IA;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class BotListener implements EventListener {

    @Override
    public void onEvent(GenericEvent event) {
        System.out.println(event.getClass().getSimpleName());
        if (event instanceof GuildMessageReceivedEvent) onMessage((GuildMessageReceivedEvent) event);
        if (event instanceof PrivateMessageReceivedEvent) onPrivateMessage((PrivateMessageReceivedEvent) event);
    }

    private void onMessage(GuildMessageReceivedEvent event) {

        final String message = event.getMessage().getContentDisplay();

        if (event.getAuthor().isBot()
                || event.getAuthor().equals(event.getJDA().getSelfUser())
                || message.isEmpty()) {
            return;
        }

        if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)
                && !CmdUtils.isCommand(message)
                && message.toLowerCase().contains("greffier")) {

            Message answer = IA.getAnswer(message);
            if (answer != null) {
                event.getChannel().sendTyping().queue();
                delay();
                event.getChannel().sendMessage(answer).queue();
                System.out.println("IA has spoken");
            }

        }

        if (message.toLowerCase().contains("bayolante")
                && event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION)) {

            event.getMessage().addReaction("\ud83C\uDDE7").queue();
            event.getMessage().addReaction("\uD83C\uDD70").queue();
            event.getMessage().addReaction("\uD83C\uDDFE").queue();
            event.getMessage().addReaction("\uD83C\uDDF4").queue();
            event.getMessage().addReaction("\uD83C\uDDF1").queue();
            event.getMessage().addReaction("\uD83C\uDDE6").queue();
            event.getMessage().addReaction("\uD83C\uDDF3").queue();
            event.getMessage().addReaction("\uD83C\uDDF9").queue();
            event.getMessage().addReaction("\uD83C\uDDEA").queue();
            System.out.println("Bayolante");

        }

    }

    private void onPrivateMessage(PrivateMessageReceivedEvent event) {

        final String message = event.getMessage().getContentDisplay();

        if (event.getAuthor().isBot()
                || event.getAuthor().equals(event.getJDA().getSelfUser())
                || message.isEmpty()) {
            return;
        }

        if (!CmdUtils.isCommand(message)) {

            Message answer = IA.getAnswer(message);
            if (answer != null) {
                event.getChannel().sendTyping().queue();
                delay();
                event.getChannel().sendMessage(answer).queue();
                System.out.println("IA has spoken");
            }

        }

    }

    private static void delay() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}