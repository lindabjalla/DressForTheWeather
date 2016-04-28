package se.hayumi.dressfortheweather.model;

import org.apache.commons.lang3.RandomStringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class WeatherData {

    private String entryId;
    private String dateTime;
    private String condition;
    private int temperature;
    private int feelsLikeTemperature;
    private String clothesToWear;
    private String fetchTime;

    public WeatherData(String dateTime, String condition, int temperature, int feelsLikeTemperature) {

        entryId = RandomStringUtils.randomAlphanumeric(8);
        this.dateTime = dateTime;
        this.condition = condition;
        this.temperature = temperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        addSuitableClothes();
        addUmbrella();
        Date date = new Date();
        fetchTime = new SimpleDateFormat("yyyy-MM-dd HH", new Locale("sv", "SE")).format(date);
    }

    public void addUmbrella() {

        if (condition.toLowerCase().contains("rain") || condition.toLowerCase().contains("shower")) {
            clothesToWear = clothesToWear.replace("]", "");
            clothesToWear += ", umbrella]";
        }
    }

    public void addSuitableClothes() {

        if (feelsLikeTemperature >= 25) {

            clothesToWear = "[T-shirt]";

        } else if (feelsLikeTemperature >= 21) {

            clothesToWear = "[long-sleeved shirt]";

        } else if (feelsLikeTemperature >= 15) {

            clothesToWear = "[cardigan]";

        } else if (feelsLikeTemperature >= 11) {

            clothesToWear = "[knitted sweater]";

        } else if (feelsLikeTemperature >= 5) {

            clothesToWear = "[winter jacket]";

        } else if (feelsLikeTemperature <= 4) {

            clothesToWear = "[winter jacket, scarf, mittens]";

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

    public String getFeelsLikeTemperature() {

        return "Feels like " + feelsLikeTemperature + "°C";
    }

    public String getFetchTime() {

        return fetchTime;
    }

    public void setEntryId(String entryId) {

        this.entryId = entryId;
    }

    public void setDateTime(String dateTime) {

        this.dateTime = dateTime;
    }

    public void setCondition(String condition) {

        this.condition = condition;
    }

    public void setTemperature(int temperature) {

        this.temperature = temperature;
    }

    public void setFeelsLikeTemperature(int feelsLikeTemperature) {

        this.feelsLikeTemperature = feelsLikeTemperature;
    }

    public void setClothesToWear(String clothesToWear) {

        this.clothesToWear = clothesToWear;
    }

    public void setFetchTime(String fetchTime) {

        this.fetchTime = fetchTime;
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
                ", clothesToWear=" + clothesToWear +
                ", fetchedTime=" + fetchTime +
                '}';
    }
}
