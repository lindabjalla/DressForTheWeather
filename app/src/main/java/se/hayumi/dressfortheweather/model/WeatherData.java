package se.hayumi.dressfortheweather.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

public final class WeatherData {

    private final String entryId;
    private final String dateTime;
    private final String condition;
    private final int temperature;
    private final int feelsLikeTemperature;
    private List<String> clothesToWear;
    private String fetchedTime;

    public WeatherData(String dateTime, String condition, int temperature, int feelsLikeTemperature) {

        entryId = RandomStringUtils.randomAlphanumeric(8);
        this.dateTime = dateTime;
        this.condition = condition;
        this.temperature = temperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        clothesToWear = new ArrayList<>();
        addSuitableClothes();
        addUmbrella();
        Date date = new Date();
        fetchedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("sv", "SE")).format(date);
    }

    public void addUmbrella() {

        if (condition.toLowerCase().contains("rain") || condition.toLowerCase().contains("shower")) {

            clothesToWear.add("umbrella");
        }
    }

    public void addSuitableClothes() {

        if (feelsLikeTemperature >= 25) {

            clothesToWear.add("T-shirt");

        } else if (feelsLikeTemperature >= 21) {

            clothesToWear.add("long-sleeved shirt");

        } else if (feelsLikeTemperature >= 15) {

            clothesToWear.add("cardigan");

        } else if (feelsLikeTemperature >= 11) {

            clothesToWear.add("knitted sweater");

        } else if (feelsLikeTemperature >= 5) {

            clothesToWear.add("winter jacket");

        } else if (feelsLikeTemperature <= 4) {

            clothesToWear.add("winter jacket");
            clothesToWear.add("scarf");
            clothesToWear.add("mittens");

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

    public List<String> getClothesToWear() {

        return clothesToWear;
    }

    public String getCondition() {

        return condition;
    }

    public String getTemperature() {

        return temperature + "°C";
    }

    public String getFeelsLikeTemperature() {

        return "Feels like " + feelsLikeTemperature + "°C";
    }

    public String getFetchedTime() {

        return fetchedTime;
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {

            return true;
        }
        if (other instanceof WeatherData) {

            WeatherData otherWeatherData = (WeatherData) other;
            return entryId.equals(otherWeatherData.getEntryId()) && fetchedTime.equals(otherWeatherData.fetchedTime);
        }
        return false;
    }

    @Override
    public int hashCode() {

        int result = 1;
        result = 37 * result + (entryId != null ? entryId.hashCode() : 0);
        result = 37 * result + (fetchedTime != null ? fetchedTime.hashCode() : 0);

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
                ", clothesToWear=" + clothesToWear +
                ", fetchedTime=" + fetchedTime +
                '}';
    }
}
