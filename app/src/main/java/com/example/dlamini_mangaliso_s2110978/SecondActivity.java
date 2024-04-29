/**
 *
 * @author Mangaliso Linda Dlamini S2110978
 */
package com.example.dlamini_mangaliso_s2110978;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    FunctionsClass fc = new FunctionsClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        ForecastWeather forecast = (ForecastWeather) intent.getSerializableExtra("forecast");
        int i = (int) intent.getSerializableExtra("i");
        Weather weather = forecast.getForecastWeather();

        TextView city = findViewById(R.id.city);
        TextView country = findViewById(R.id.country);
        fc.setLocation(city, country, i);

        TextView min = findViewById(R.id.min_temperature);
        min.setText(forecast.getMinTemperature());

        TextView max = findViewById(R.id.max_temperature);
        max.setText(forecast.getMaxTemperature());

        TextView pubDate = findViewById(R.id.updatedAt);
        pubDate.append(weather.getDate());

        TextView humidity = findViewById(R.id.humidity_value);
        humidity.setText(weather.getHumidity());

        TextView wind= findViewById(R.id.wind_value);
        wind.setText(weather.getWindSpeed());
        wind.append("/");
        wind.append(weather.getWindDirection());

        TextView pressure = findViewById(R.id.pressure_value);
        pressure.setText(weather.getPressure());

        TextView condition = findViewById(R.id.condition_description);
        condition.setText(weather.getCondition());

        ImageView weather_icon = findViewById(R.id.weather_icon);
        fc.SetWeatherIcon(weather_icon, weather.getCondition());

        TextView visibility = findViewById(R.id.visibility_value);
        visibility.setText(forecast.getVisibility());

        TextView uv = findViewById(R.id.uv_value);
        uv.setText(forecast.getUv_risk());

        TextView sunset = findViewById(R.id.sunset_time);
        sunset.setText(forecast.getSunset());

        TextView sunrise = findViewById(R.id.sunrise_time);
        sunrise.setText(forecast.getSunrise());




    }

}