package se.hayumi.dressfortheweather.model;

import com.google.gson.annotations.SerializedName;

public class FeelsLikeTemperature {

    @SerializedName("english")
    private int fahrenheit;

    @SerializedName("metric")
    private int celsius;

    public int getCelsius() {
        return celsius;
    }

    @Override
    public String toString() {
        return "FeelsLikeTemperature{" +
                "fahrenheit=" + fahrenheit +
                ", celsius=" + celsius +
                '}';
    }
}
