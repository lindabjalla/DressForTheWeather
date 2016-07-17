package se.hayumi.dressfortheweather.model;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("FCTTIME")
    private Time time;

    private String condition;

    @SerializedName("temp")
    private Temperature temperature;

    @SerializedName("feelslike")
    private FeelsLikeTemperature feelsLikeTemperature;

    public Time getTime() {
        return time;
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
                "dateTime='" + time + '\'' +
                ", condition='" + condition + '\'' +
                ", temperature=" + temperature +
                ", feelsLikeTemperature=" + feelsLikeTemperature +
                '}';
    }
}
