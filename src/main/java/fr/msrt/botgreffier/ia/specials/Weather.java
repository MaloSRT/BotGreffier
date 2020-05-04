package fr.msrt.botgreffier.ia.specials;

import net.dv8tion.jda.api.MessageBuilder;
import org.openweathermap.api.model.currentweather.CurrentWeather;

import static fr.msrt.botgreffier.features.Weather.getCurrentWeather;
import static fr.msrt.botgreffier.features.Weather.getEmbed;

public class Weather {

    public static MessageBuilder getMessageBuilder(String args) {
        CurrentWeather currentWeather = getCurrentWeather(args);
        if (currentWeather == null || currentWeather.getCityName().equals("0")) {
            return null;
        } else {
            return new MessageBuilder().setEmbed(getEmbed(currentWeather).build());
        }
    }

}