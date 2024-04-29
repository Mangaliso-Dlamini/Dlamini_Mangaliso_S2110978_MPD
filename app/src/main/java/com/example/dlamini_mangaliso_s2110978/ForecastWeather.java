/**
 *
 * @author Mangaliso Linda Dlamini S2110978
 */
package com.example.dlamini_mangaliso_s2110978;

import java.io.Serializable;

public class ForecastWeather implements Serializable {
    private Weather forecastWeather;
    private String maxTemperature;
    private String minTemperature;
    private String sunrise;
    private String sunset;

    private String visibility;

    private String uv_risk;

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getUv_risk() {
        return uv_risk;
    }

    public void setUv_risk(String uv_risk) {
        this.uv_risk = uv_risk;
    }

    public Weather getForecastWeather() {
        return forecastWeather;
    }

    public void setForecastWeather(Weather forecastWeather) {
        this.forecastWeather = forecastWeather;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

// Display forecasted weather method

    @Override
    public String toString() {
        return "ForecastWeather{" + "forecastWeather=" + forecastWeather + ", maxTemperature=" + maxTemperature + ", minTemperature=" + minTemperature + '}';
    }

}
