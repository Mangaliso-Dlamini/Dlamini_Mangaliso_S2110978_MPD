/**
 *
 * @author Mangaliso Linda Dlamini S2110978
 */
package com.example.dlamini_mangaliso_s2110978;

import java.io.Serializable;

public class Weather implements Serializable {
    private String location;
    private String date;
    private String dayOfWeek;
    private String temperature;
    private String humidity;
    private String pressure;
    private String windSpeed;
    private String windDirection;
    private String condition;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }


    // Display weather method

    @Override
    public String toString() {
        return "Weather{" + "location=" + location + ", date=" + date + ", dayOfWeek=" + dayOfWeek + ", temperature=" + temperature + ", humidity=" + humidity + ", pressure=" + pressure + ", windSpeed=" + windSpeed + ", windDirection=" + windDirection + ", condition=" + condition + '}';
    }


}

