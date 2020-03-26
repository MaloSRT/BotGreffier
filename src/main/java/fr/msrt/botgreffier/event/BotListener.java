package fr.msrt.botgreffier.event;

import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.ia.IA;
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
                && !event.getMessage().getContentDisplay().substring(0, Constants.PREFIX.length()).equals(Constants.PREFIX)
                && event.getMessage().getContentDisplay().toLowerCase().contains("greffier")) {

            String answer = IA.getAnswer(event.getMessage().getContentDisplay());
            if (!"0".equals(answer)) {
                event.getChannel().sendTyping().queue();
                delay();
                event.getChannel().sendMessage(answer).queue();
                System.out.println("IA has spoken");
            }

        }

        if (event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_ADD_REACTION)
                && event.getMessage().getContentDisplay().toLowerCase().contains("bayolante")) {

            event.getMessage().addReaction("\ud83c\udde7").queue();
            event.getMessage().addReaction("\ud83c\udd70").queue();
            event.getMessage().addReaction("\ud83c\uddfe").queue();
            event.getMessage().addReaction("\ud83c\uddf4").queue();
            event.getMessage().addReaction("\ud83c\uddf1").queue();
            event.getMessage().addReaction("\ud83c\udde6").queue();
            event.getMessage().addReaction("\ud83c\uddf3").queue();
            event.getMessage().addReaction("\ud83c\uddf9").queue();
            event.getMessage().addReaction("\ud83c\uddea").queue();
            System.out.println("Bayolante");

        }

    }

    private void onPrivateMessage(PrivateMessageReceivedEvent event) {

        if (event.getAuthor().equals(event.getJDA().getSelfUser()) || event.getMessage().getContentDisplay().isEmpty()) return;

        if (!event.getMessage().getContentDisplay().substring(0, Constants.PREFIX.length()).equals(Constants.PREFIX)) {

            String answer = IA.getAnswer(event.getMessage().getContentDisplay());
            if (!"0".equals(answer)) {
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
