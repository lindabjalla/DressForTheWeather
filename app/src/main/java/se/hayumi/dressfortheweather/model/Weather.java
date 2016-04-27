package se.hayumi.dressfortheweather.model;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("pretty")
    private String dateTime;
    private String condition;
    @SerializedName("temp")
    private Temperature temperature;
    @SerializedName("feelslike")
    private FeelsLikeTemperature feelsLikeTemperature;

    public String getDateTime() {
        return dateTime;
    }

    public String getCondition() {
        return condition;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public FeelsLikeTemperature getFeelsLikeTemperature() {
        return feelsLikeTemperature;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "dateTime='" + dateTime + '\'' +
                ", condition='" + condition + '\'' +
                ", temperature=" + temperature +
                ", feelsLikeTemperature=" + feelsLikeTemperature +
                '}';
    }
}
