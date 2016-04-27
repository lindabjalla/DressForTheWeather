package se.hayumi.dressfortheweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HourlyForecast {

    @SerializedName("hourly_forecast")
    List<WeatherData> weatherDataList;

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    @Override
    public String toString() {
        return "HourlyForecast{" +
                "weatherDataList=" + weatherDataList +
                '}';
    }
}
