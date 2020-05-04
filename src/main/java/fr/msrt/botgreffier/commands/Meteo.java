package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.Constants;
import fr.msrt.botgreffier.features.Weather;
import fr.msrt.botgreffier.utils.CmdUtils;
import net.dv8tion.jda.api.Permission;
import org.openweathermap.api.model.currentweather.CurrentWeather;

public class Meteo extends Command {

    public Meteo() {
        this.name = "météo";
        this.aliases = new String[]{"meteo", "weather"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (event.getArgs().isEmpty()) {

            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay(), "[ville]"));

        } else {

            CurrentWeather currentWeather = Weather.getCurrentWeather(event.getArgs());

            if (currentWeather == null) {
                event.reply(Constants.ERR_PROG);
            } else if (currentWeather.getCityName().equals("0")) {
                event.reply(Constants.EMOTE_DOUBT + " **Ville non trouvée**");
            } else {
                event.reply(Weather.getEmbed(currentWeather).build());
            }

            CmdUtils.sysoutCmd(event.getMessage().getContentDisplay());

        }

    }

}