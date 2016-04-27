package se.hayumi.dressfortheweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HourlyForecast {

    @SerializedName("hourly_forecast")
    List<Weather> weatherList;

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    @Override
    public String toString() {
        return "HourlyForecast{" +
                "weatherList=" + weatherList +
                '}';
    }
}
