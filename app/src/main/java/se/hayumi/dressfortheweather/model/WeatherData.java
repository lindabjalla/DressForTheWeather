package se.hayumi.dressfortheweather.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmObject;
import se.hayumi.dressfortheweather.R;

public class WeatherData extends RealmObject implements Parcelable {

    private String entryId;
    private String dateTime;
    private String condition;
    private int temperature;
    private int feelsLikeTemperature;
    private String clothesToWear;
    private int dateTimeInMilliSeconds;;
    private String fetchTime;

    public WeatherData() {}

    public WeatherData(String dateTime, String condition, int temperature, int feelsLikeTemperature, int dateTimeInMilliSeconds) {

        entryId = RandomStringUtils.randomAlphanumeric(8);
        this.dateTime = dateTime;
        this.condition = condition;
        this.temperature = temperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        Date date = new Date();
        fetchTime = new SimpleDateFormat("yyyy-MM-dd HH", new Locale("sv", "SE")).format(date);
        this.dateTimeInMilliSeconds = dateTimeInMilliSeconds;;
    }

    protected WeatherData(Parcel in) {
        entryId = in.readString();
        dateTime = in.readString();
        condition = in.readString();
        temperature = in.readInt();
        feelsLikeTemperature = in.readInt();
        clothesToWear = in.readString();
        dateTimeInMilliSeconds = in.readInt();
        fetchTime = in.readString();
    }

    public static final Creator<WeatherData> CREATOR = new Creator<WeatherData>() {

        @Override
        public WeatherData createFromParcel(Parcel in) {
            return new WeatherData(in);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(entryId);
        out.writeString(dateTime);
        out.writeString(condition);
        out.writeInt(temperature);
        out.writeInt(feelsLikeTemperature);
        out.writeString(clothesToWear);
        out.writeInt(dateTimeInMilliSeconds);
        out.writeString(fetchTime);
    }

    public void addUmbrella(Context context) {

        if (condition.toLowerCase().contains(context.getString(R.string.rain)) || condition.toLowerCase().contains("shower")) {
            clothesToWear = clothesToWear.replace("]", "");
            clothesToWear += context.getString(R.string.umbrella);
        }
    }

    public void addSuitableClothes(Context context) {

        if (feelsLikeTemperature >= 25) {

            clothesToWear = context.getString(R.string.tShirt);

        } else if (feelsLikeTemperature >= 21) {

            clothesToWear = context.getString(R.string.longSleevedShirt);

        } else if (feelsLikeTemperature >= 16) {

            clothesToWear = context.getString(R.string.cardigan);

        } else if (feelsLikeTemperature >= 11) {

            clothesToWear = context.getString(R.string.sweater);

        } else if (feelsLikeTemperature >= 6) {

            clothesToWear = context.getString(R.string.jacket);

        } else if (feelsLikeTemperature <= 5) {

            clothesToWear = context.getString(R.string.paddedCoat);

        } else {

            throw new IllegalArgumentException("feelsLikeTemperature must be a numeric value");
        }
    }

    public String getEntryId() {

        return entryId;
    }

    public String getDateTime() {

        return dateTime;
    }

    public String getClothesToWear() {

        return clothesToWear;
    }

    public String getCondition() {

        return condition;
    }

    public String getTemperature() {

        return temperature + "°C";
    }

    public String getFeelsLikeTemperature(Context context) {

        return context.getString(R.string.feelslike) + feelsLikeTemperature + "°C";
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {

            return true;
        }
        if (other instanceof WeatherData) {

            WeatherData otherWeatherData = (WeatherData) other;
            return entryId.equals(otherWeatherData.getEntryId()) && fetchTime.equals(otherWeatherData.fetchTime);
        }
        return false;
    }

    @Override
    public int hashCode() {

        int result = 1;
        result = 37 * result + (entryId != null ? entryId.hashCode() : 0);
        result = 37 * result + (fetchTime != null ? fetchTime.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "entryId='" + entryId + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", condition='" + condition + '\'' +
                ", temperature=" + temperature +
                ", feelsLikeTemperature=" + feelsLikeTemperature +
                ", clothesToWear='" + clothesToWear + '\'' +
                ", dateTimeInMilliSeconds=" + dateTimeInMilliSeconds +
                ", fetchTime='" + fetchTime + '\'' +
                '}';
    }
}
