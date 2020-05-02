package fr.msrt.botgreffier.features;

import fr.msrt.botgreffier.config.Config;
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
                System.err.println(e.toString());
            }

        }

        return null;
    }

}