package se.hayumi.dressfortheweather.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import se.hayumi.dressfortheweather.model.HourlyForecast;

public interface WeatherService {

    @GET("hourly/q/se/{local}")
    Call<HourlyForecast> getHourlyWeather(@Path("local") String local);


//    private final Context context;
//    private final WeatherDataSource dataSource;
//
//    public WeatherService(Context context) {
//        this.context = context;
//        dataSource = new WeatherDataSource(context);
//    }
//
//    public List<WeatherData> getWeather() {
//
//        final List<WeatherData> weatherList = new ArrayList<>();
//
//        Ion.with(context)
//                .load("http://api.wunderground.com/api/acac042dd991e6be/hourly/q/se/stockholm.json")
//                .asJsonObject()
//                .setCallback(new FutureCallback<JsonObject>() {
//
//                    @Override
//                    public void onCompleted(Exception e, JsonObject result) {
//                        System.out.println(result);
//                        final JsonArray hourlyForecastList = result.get("hourly_forecast").getAsJsonArray();
//                        System.out.println(hourlyForecastList);
//                        convertJsonToWeatherData(hourlyForecastList);
//                        List<WeatherData> jsonToWeatherData = convertJsonToWeatherData(hourlyForecastList);
//                        System.out.println(jsonToWeatherData);
//                        weatherList.addAll(jsonToWeatherData);
//
//                        System.out.println(weatherList);
//                    }
//                });
//        return weatherList;
//    }
//
//    private List<WeatherData> convertJsonToWeatherData(JsonArray hourlyForecastList) {
//
//        final List<WeatherData> weatherList = new ArrayList<>();
//
//        for (JsonElement hourlyForecast : hourlyForecastList) {
//
//            final JsonObject hourlyForecastJsonObject = hourlyForecast.getAsJsonObject();
//            String dateTime = hourlyForecastJsonObject.get("FCTTIME").getAsJsonObject()
//                    .get("pretty").getAsString();
//            String condition = hourlyForecastJsonObject.get("condition").getAsString();
//            int temperature = hourlyForecastJsonObject.get("temp").getAsJsonObject()
//                    .get("metric").getAsInt();
//            int feelsLikeTemperature = hourlyForecastJsonObject.get("feelslike").getAsJsonObject()
//                    .get("metric").getAsInt();
//
//            WeatherData weather = new WeatherData(dateTime, condition, temperature, feelsLikeTemperature);
//            weatherList.add(weather);
//            dataSource.insertWeather(weather);
//        }
//        return weatherList;
//    }
}
