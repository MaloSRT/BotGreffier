package fr.msrt.botgreffier.features;

import fr.msrt.botgreffier.config.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import org.openweathermap.api.DataWeatherClient;
import org.openweathermap.api.UrlConnectionDataWeatherClient;
import org.openweathermap.api.WeatherClientRequestException;
import org.openweathermap.api.model.currentweather.CurrentWeather;
import org.openweathermap.api.query.Language;
import org.openweathermap.api.query.QueryBuilderPicker;
import org.openweathermap.api.query.ResponseFormat;
import org.openweathermap.api.query.Type;
import org.openweathermap.api.query.UnitFormat;
import org.openweathermap.api.query.currentweather.CurrentWeatherOneLocationQuery;

import java.awt.*;

public class Weather {

    public static CurrentWeather getCurrentWeather(String city) {

        try {

            DataWeatherClient client = new UrlConnectionDataWeatherClient(Config.getStringValue("owm_api_key"));
            CurrentWeatherOneLocationQuery currentWeatherOneLocationQuery = QueryBuilderPicker.pick()
                    .currentWeather()
                    .oneLocation()
                    .byCityName(city)
                    .type(Type.ACCURATE)
                    .language(Language.FRENCH)
                    .responseFormat(ResponseFormat.JSON)
                    .unitFormat(UnitFormat.METRIC)
                    .build();
            return client.getCurrentWeather(currentWeatherOneLocationQuery);

        } catch (WeatherClientRequestException e) {

            if (e.toString().startsWith("org.openweathermap.api.WeatherClientRequestException: java.io.FileNotFoundException: http://api.openweathermap.org/data/")) {
                CurrentWeather currentWeather = new CurrentWeather();
                currentWeather.setCityName("0");
                return currentWeather;
            } else {
                System.err.println(e);
            }

        }

        return null;
    }

    public static EmbedBuilder getEmbed(CurrentWeather currentWeather) {

        return new EmbedBuilder()
                .setAuthor("Météo de " + currentWeather.getCityName()
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

    }

}