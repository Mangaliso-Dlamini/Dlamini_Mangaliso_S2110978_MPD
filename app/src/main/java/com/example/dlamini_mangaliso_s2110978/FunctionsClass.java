/**
 *
 * @author Mangaliso Linda Dlamini S2110978
 */
package com.example.dlamini_mangaliso_s2110978;

import android.widget.ImageView;
import android.widget.TextView;

public class FunctionsClass {

    public void setLocation(TextView city, TextView country, int i){
        switch (i){
            case 0:
                city.setText("Glasgow");
                country.setText("Scotland");
                break;
            case 1:
                city.setText("London");
                country.setText("England");
                break;
            case 2:
                city.setText("New York");
                country.setText("USA");
                break;
            case 3:
                city.setText("Muscat");
                country.setText("Oman");
                break;
            case 4:
                city.setText("Port Louis");
                country.setText("Mauritius");
                break;
            case 5:
                city.setText("Dhaka");
                country.setText("Bangladesh");
                break;

        }
    }
    public void SetWeatherIcon(ImageView iv, String conditions){
        switch (conditions){
            case "Sunny":
                iv.setImageResource(R.drawable.day_clear);
                break;
            case "Sunny Intervals":
                iv.setImageResource(R.drawable.day_partial_cloud);
                break;
            case "Clear Sky":
                iv.setImageResource(R.drawable.night_clear);
                break;
            case "Light Rain Shower":
                iv.setImageResource(R.drawable.rain);
                break;
            case "Mist":
                iv.setImageResource(R.drawable.mist);
                break;
            case "Fog":
                iv.setImageResource(R.drawable.fog);
                break;
            case "Light Rain":
                iv.setImageResource(R.drawable.rain);
                break;
            case "Heavy Rain":
                iv.setImageResource(R.drawable.rain);
                break;
            case "Light Cloud":
                iv.setImageResource(R.drawable.rain);
                break;
            case "Thundery Shower":
                iv.setImageResource(R.drawable.rain);
                break;
            default:
                iv.setImageResource(R.drawable.overcast);
                break;

        }
    }
}
