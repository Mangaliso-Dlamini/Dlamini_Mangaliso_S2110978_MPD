/**
 *
 * @author Mangaliso Linda Dlamini S2110978
 */
package com.example.dlamini_mangaliso_s2110978;

public class CurrentObservation {
    private Weather currentWeather;

    // Getter for current weather
    public Weather getCurrentWeather() {
        return currentWeather;
    }

    // Display current weather method

    @Override
    public String toString() {
        return "CurrentObservation{" + "currentWeather=" + currentWeather + '}';
    }

    public void setCurrentWeather(Weather currentWeather) {
        this.currentWeather = currentWeather;
    }

}

