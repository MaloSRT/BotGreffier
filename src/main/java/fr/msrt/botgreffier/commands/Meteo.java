package fr.msrt.botgreffier.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.msrt.botgreffier.features.Weather;
import fr.msrt.botgreffier.utils.CmdUtils;
import fr.msrt.botgreffier.utils.SysoutCmd;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import org.openweathermap.api.model.currentweather.CurrentWeather;

import java.awt.*;

public class Meteo extends Command {

    public Meteo() {
        this.name = "météo";
        this.aliases = new String[]{"meteo", "weather"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {

        if (event.getArgs().isEmpty()) {

            event.reply(CmdUtils.warnSyntax(event.getMessage().getContentDisplay() + " [ville]"));

        } else {

            CurrentWeather currentWeather = Weather.getCurrentWeather(event.getArgs());

            assert currentWeather != null;
            if (currentWeather.getCityName().equals("0")) {
                event.reply(":question: **Ville non trouvée**");
            } else {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setAuthor("Météo de " + currentWeather.getCityName()
                                + " (" + currentWeather.getSystemParameters().getCountry() + ")",
                        null,
                        "https://openweathermap.org/themes/openweathermap/assets/vendor/owm/img/widgets/"
                                + currentWeather.getWeather().get(0).getIcon() + ".png")
                        .setThumbnail("https://openweathermap.org/img/wn/"
                                + currentWeather.getWeather().get(0).getIcon() + "@2x.png")
                        .setDescription(currentWeather.getWeather().get(0).getDescription().substring(0, 1).toUpperCase()
                                + currentWeather.getWeather().get(0).getDescription().substring(1))
                        .addField("Température", currentWeather.getMainParameters().getTemperature() + " °C\n"
                                + "Min : " + currentWeather.getMainParameters().getMinimumTemperature() + " °C / "
                                + "Max : " + currentWeather.getMainParameters().getMaximumTemperature() + " °C", false)
                        .addField("Vent", currentWeather.getWind().getSpeed() + " km/h", true)
                        .addField("Humidité", currentWeather.getMainParameters().getHumidity() + " %", true)
                        .addField("Pression", currentWeather.getMainParameters().getPressure() + " hPa", true)
                        .setColor(Color.ORANGE);
                event.reply(embed.build());
            }

            SysoutCmd.sysoutCmd(event.getMessage().getContentDisplay());

        }

    }

}
