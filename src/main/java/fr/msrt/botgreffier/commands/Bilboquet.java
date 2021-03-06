package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.utils.CmdUtils;

import java.util.Random;

public class Bilboquet extends Command {

    public Bilboquet() {
        this.name = "bilboquet";
        this.aliases = new String[]{"bilbo"};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());
        event.reply("``` \n \n \n       1\n      {_}\n       I\\\n         \\\n          \\\n           o\n ```", m -> {
            Random random = new Random();
            delay(300);
            m.editMessage("``` \n \n \n       1\n      {_}  o\n       I\\_/\n \n \n \n ```").queue();
            delay(300);
            m.editMessage("``` \n \n \n       1   o\n      {_} /\n       I\\/\n \n \n \n ```").queue();
            delay(300);
            m.editMessage("``` \n \n         o\n       1  \\\n      {_} /\n       I\\/\n \n \n \n ```").queue();
            delay(300);
            if (random.nextInt(4) == 0) {
                m.editMessage("``` \n       o\n        \\\n       1 \\\n      {_}/\n       I\n \n \n \n ```").queue();
                delay(300);
                m.editMessage("``` \n       _\n     o' \\\n       1 \\\n      {_}/\n       I\n \n \n \n ```").queue();
                delay(300);
                m.editMessage("``` \n \n       __\n      /1 \\\n     o{_}/\n       I\n \n \n \n ```").queue();
                delay(800);
                m.editMessage("``` \n \n       __\n      /1 \\\n     o{_}/\n       I\n \n \n \n ```" + Constants.EMOTE_DEFEAT + " **ÉCHEC !**").queue();
            } else {
                m.editMessage("``` \n         o\n          \\\n       1  /\n      {_}/\n       I\n \n \n \n ```").queue();
                delay(300);
                m.editMessage("``` \n \n       o-_\n       1  \\\n      {_}_/\n       I\n \n \n \n ```").queue();
                delay(300);
                m.editMessage("``` \n \n         _\n       o' \\\n      {_}_/\n       I\n \n \n \n ```").queue();
                delay(800);
                m.editMessage("``` \n \n         _\n       o' \\\n      {_}_/\n       I\n \n \n \n ```" + Constants.EMOTE_VICTORY + " **VICTOIRE !**").queue();
            }
        });
        System.out.println("BilboquetFin");

    }

    private static void delay(int delayMilliseconds) {

        try {
            Thread.sleep(delayMilliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}