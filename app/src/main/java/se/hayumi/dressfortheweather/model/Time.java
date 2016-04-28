package se.hayumi.dressfortheweather.model;

import com.google.gson.annotations.SerializedName;

public final class Time {

    @SerializedName("pretty")
    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Time{" +
                "dateTime='" + dateTime + '\'' +
                '}';
    }
}
