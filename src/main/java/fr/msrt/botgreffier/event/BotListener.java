package fr.msrt.botgreffier.event;

import fr.msrt.botgreffier.ia.IAv2;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class BotListener implements EventListener {

    @Override
    public void onEvent(GenericEvent event) {
        System.out.println(event.getClass().getSimpleName());
        if (event instanceof GuildMessageReceivedEvent) onMessage((GuildMessageReceivedEvent)event);
        if (event instanceof PrivateMessageReceivedEvent) onPrivateMessage((PrivateMessageReceivedEvent)event);
    }

    private void onMessage(GuildMessageReceivedEvent event) {

        if (event.getAuthor().equals(event.getJDA().getSelfUser()) || event.getMessage().getContentDisplay().isEmpty()) return;

        if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)
                && !CmdUtils.isCommand(event.getMessage().getContentDisplay())
                && event.getMessage().getContentDisplay().toLowerCase().contains("gréfié")) {

            String answer = IAv2.getAnswer(event.getMessage().getContentDisplay());
            if (answer != null) {
                event.getChannel().sendTyping().queue();
                delay();
                event.getChannel().sendMessage(answer).queue();
                System.out.println("IA has spoken");
            }

        }

        if (event.getMessage().getContentDisplay().toLowerCase().contains("bayolante")
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

        if (event.getAuthor().equals(event.getJDA().getSelfUser()) || event.getMessage().getContentDisplay().isEmpty()) return;

        if (!CmdUtils.isCommand(event.getMessage().getContentDisplay())) {

            String answer = IAv2.getAnswer(event.getMessage().getContentDisplay());
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