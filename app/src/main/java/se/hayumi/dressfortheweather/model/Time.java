package se.hayumi.dressfortheweather.model;

import com.google.gson.annotations.SerializedName;

public final class Time {

    @SerializedName("pretty")
    private String dateTime;

    @SerializedName("epoch")
    private int dateTimeInMilliSeconds;

    public String getDateTime() {
        return dateTime;
    }

    public int getDateTimeInMilliSeconds() {
        return dateTimeInMilliSeconds;
    }

    @Override
    public String toString() {
        return "Time{" +
                "dateTime='" + dateTime + '\'' +
                ", dateTimeInMilliSeconds='" + dateTimeInMilliSeconds + '\'' +
                '}';
    }
}
