package se.hayumi.dressfortheweather.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import se.hayumi.dressfortheweather.model.HourlyForecast;

public interface WeatherService {

    @GET("hourly/q/se/{local}")
    Call<HourlyForecast> getHourlyWeather(@Path("local") String local);
}
