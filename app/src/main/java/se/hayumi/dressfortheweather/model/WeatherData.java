package se.hayumi.dressfortheweather.model;

import java.util.ArrayList;
import java.util.List;

public final class WeatherData {

    private final String dateTime;
    private final String condition;
    private final int temperature;
    private final int feelsLikeTemperature;
    private List<String> suitableClothes;

    public WeatherData(String dateTime, String condition, int temperature, int feelsLikeTemperature) {
        this.dateTime = dateTime;
        this.condition = condition;
        this.temperature = temperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        suitableClothes = new ArrayList<>();
        addSuitableClothes();
        addUmbrella();
    }

    public void addUmbrella() {
        if (condition.toLowerCase().contains("rain")) {
            suitableClothes.add("umbrella");
        }
    }

    public void addSuitableClothes() {
        if (feelsLikeTemperature >= 25) {
            suitableClothes.add("T-shirt");
        } else if (feelsLikeTemperature >= 21) {
            suitableClothes.add("long-sleeved shirt");
        } else if (feelsLikeTemperature >= 15) {
            suitableClothes.add("cardigan");
        } else if (feelsLikeTemperature >= 11) {
            suitableClothes.add("knitted sweater");
        } else if (feelsLikeTemperature >= 5) {
            suitableClothes.add("winter jacket");
        } else if (feelsLikeTemperature < 4) {
            suitableClothes.add("winter jacket");
            suitableClothes.add("scarf");
            suitableClothes.add("mittens");
        } else {
            throw new IllegalArgumentException("feelsLikeTemperature must be a numeric value");
        }
    }

    public String getDateTime() {
        return dateTime;
    }

    public List<String> getSuitableClothes() {
        return suitableClothes;
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
}
