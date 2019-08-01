package fr.msrt.botgreffier.features;

import fr.msrt.botgreffier.BotGreffier;
import org.openweathermap.api.DataWeatherClient;
import org.openweathermap.api.UrlConnectionDataWeatherClient;
import org.openweathermap.api.WeatherClientRequestException;
import org.openweathermap.api.model.currentweather.CurrentWeather;
import org.openweathermap.api.query.*;
import org.openweathermap.api.query.currentweather.CurrentWeatherOneLocationQuery;

public class Meteo {

    private static final String API_KEY = BotGreffier.CONFIG.getString("openweathermapAPIKey", "API_KEY");

    public CurrentWeather getCurrentWeather(String city) {

        try {

            DataWeatherClient client = new UrlConnectionDataWeatherClient(API_KEY);
            CurrentWeatherOneLocationQuery currentWeatherOneLocationQuery = QueryBuilderPicker.pick()
                    .currentWeather()
                    .oneLocation()
                    .byCityName(city)
                    .type(Type.ACCURATE)
                    .language(Language.FRENCH)
                    .responseFormat(ResponseFormat.JSON)
                    .unitFormat(UnitFormat.METRIC)
                    .build();
            CurrentWeather currentWeather = client.getCurrentWeather(currentWeatherOneLocationQuery);
            return currentWeather;

        } catch (WeatherClientRequestException e) {

            if (e.toString().substring(0, 120).equals("org.openweathermap.api.WeatherClientRequestException: java.io.FileNotFoundException: http://api.openweathermap.org/data/")) {
                CurrentWeather currentWeather = new CurrentWeather();
                currentWeather.setCityName("0");
                return currentWeather;
            } else {
                System.out.println(e.toString());
            }

        }

        return null;
    }

     /* private static String prettyPrint(CurrentWeather currentWeather) {
         return String.format(
                 "Current weather in %s(%s):\ntemperature: %.1f ℃\nhumidity: %.1f %%\npressure: %.1f hPa\n",
                 currentWeather.getCityName(), currentWeather.getSystemParameters().getCountry(),
                 currentWeather.getMainParameters().getTemperature(),
                 currentWeather.getMainParameters().getHumidity(),
                 currentWeather.getMainParameters().getPressure()
         );
     } */
}
