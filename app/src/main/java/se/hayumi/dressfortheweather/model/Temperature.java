package se.hayumi.dressfortheweather.model;

import com.google.gson.annotations.SerializedName;

public class Temperature {

    @SerializedName("english")
    private int fahrenheit;

    @SerializedName("metric")
    private int celsius;

    public int getCelsius() {
        return celsius;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "fahrenheit=" + fahrenheit +
                ", celsius=" + celsius +
                '}';
    }
}
