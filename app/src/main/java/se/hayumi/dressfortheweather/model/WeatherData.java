package se.hayumi.dressfortheweather.model;

public final class WeatherData {

    private final String dateTime;
    private final String condition;
    private final int temperature;
    private final int feelsLikeTemperature;
    private boolean needsUmbrella;
    private String suitableClothes;


    public WeatherData(String dateTime, String condition, int temperature, int feelsLikeTemperature) {
        this.dateTime = dateTime;
        this.condition = condition;
        this.temperature = temperature;
        this.feelsLikeTemperature = feelsLikeTemperature;
        needsUmbrella();
        setSuitableClothes();
    }

    public void needsUmbrella() {
        if (condition.contains("rain")) {
            needsUmbrella = true;
        }
    }

    public void setSuitableClothes() {
        if (feelsLikeTemperature >= 25) {
            suitableClothes = "T-shirt";
        } else if (feelsLikeTemperature >= 21) {
            suitableClothes = "long-sleeved shirt";
        } else if (feelsLikeTemperature >= 15) {
            suitableClothes = "cardigan";
        } else if (feelsLikeTemperature >= 11) {
            suitableClothes = "knitted sweater";
        } else if (feelsLikeTemperature >= 5) {
            suitableClothes = "winter jacket";
        } else if (feelsLikeTemperature < 4) {
            suitableClothes = "winter jacket, scarf & mittens";
        } else {
            throw new IllegalArgumentException("feelsLikeTemperature must be a numeric value");
        }
    }
}
