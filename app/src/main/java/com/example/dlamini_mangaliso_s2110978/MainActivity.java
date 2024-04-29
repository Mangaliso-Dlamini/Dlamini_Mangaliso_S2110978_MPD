/**
 *
 * @author Mangaliso Linda Dlamini S2110978
 */
package com.example.dlamini_mangaliso_s2110978;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements XMLParserListener{

    private GestureDetector gestureDetector;

    FunctionsClass fc = new FunctionsClass();
    private String[] location_id ={"2648579", "2643743", "5128581", "287286", "934154", "1185241"};
    private int i =0;
    private List<ForecastWeather> forecastWeatherList = new ArrayList<>();
   // private ConstraintS

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("iterator",i );
        //outState.putStringArray("location_ids", location_id);// Save data to the bundle
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            i = savedInstanceState.getInt("iterator"); // Retrieve data from the bundle
            //location_id = savedInstanceState.getStringArray("location_ids");
        }
        setContentView(R.layout.activity_main);

        XMLParserHandler xmlParserHandler = new XMLParserHandler(this);
        XMLParserHandler xmlParserHandler2 = new XMLParserHandler(this);
        // Execute the AsyncTask
        xmlParserHandler.execute(location_id[i], "https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/"+location_id[i]);

        xmlParserHandler2.execute(location_id[i], "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/" + location_id[i]);


        gestureDetector = new GestureDetector(this, new MyGestureListener());

        CardView day1 = findViewById(R.id.day_1_card);
        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to switch to SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("forecast", forecastWeatherList.get(0));
                intent.putExtra("i", i);
                startActivity(intent);
            }
        });

        CardView day2 = findViewById(R.id.day_2_card);
        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to switch to SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("forecast", forecastWeatherList.get(1));
                intent.putExtra("i", i);
                startActivity(intent);
            }
        });

        CardView day3 = findViewById(R.id.day_3_card);
        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to switch to SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("forecast", forecastWeatherList.get(2));
                intent.putExtra("i", i);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public void onResult(Object result) {
        // Handle the result here
        if (result instanceof CurrentObservation) {
            CurrentObservation currentObservation = (CurrentObservation) result;
            Weather weather = currentObservation.getCurrentWeather();

            TextView campus = findViewById(R.id.city);
            TextView location = findViewById(R.id.country);

            fc.setLocation(campus, location, i);

            TextView pubDate = findViewById(R.id.updatedAt);
            pubDate.append(weather.getDate());

            TextView humidity = findViewById(R.id.humidity_value);
            humidity.setText(weather.getHumidity());

            TextView wind = findViewById(R.id.wind_value);
            wind.setText(weather.getWindSpeed());
            wind.append("/");
            wind.append(weather.getWindDirection());

            TextView pressure = findViewById(R.id.pressure_value);
            pressure.setText(weather.getPressure());

            TextView condition = findViewById(R.id.condition_description);
            condition.setText(weather.getCondition());

            TextView temperature = findViewById(R.id.temperature);
            temperature.setText(weather.getTemperature());

            ImageView imageView = findViewById(R.id.weather_icon);

            fc.SetWeatherIcon(imageView, weather.getCondition());


        } else if (result instanceof List<?>) {
            List<?> forecastWeathers = (List<?>) result;
            ForecastWeather tempForecast = (ForecastWeather) forecastWeathers.get(0);
            forecastWeatherList.add(0,tempForecast);
            Weather temp = tempForecast.getForecastWeather();

            CardView CV = findViewById(R.id.day_1_card);
            ConstraintLayout CL = CV.findViewById(R.id.day_1_constraint);

            TextView day_1 = CL.findViewById(R.id.day_1);
            day_1.setText(temp.getDayOfWeek());

            TextView day_1_temperature = CL.findViewById(R.id.day_1_temperature);
            day_1_temperature.setText(tempForecast.getMinTemperature());
            day_1_temperature.append("/");
            day_1_temperature.append(tempForecast.getMaxTemperature());

            TextView day_1_conditions = CL.findViewById(R.id.day_1_conditions);
            day_1_conditions.setText(temp.getCondition());

            ImageView day_1_icon = CL.findViewById(R.id.day_1_icon);
            fc.SetWeatherIcon(day_1_icon, temp.getCondition());

            tempForecast = (ForecastWeather) forecastWeathers.get(1);
            forecastWeatherList.add(1,tempForecast);
            temp = tempForecast.getForecastWeather();

            CV = findViewById(R.id.day_2_card);
            CL = CV.findViewById(R.id.day_2_constraint);

            TextView day_2 = CL.findViewById(R.id.day_2);
            day_2.setText(temp.getDayOfWeek());

            TextView day_2_temperature = CL.findViewById(R.id.day_2_temperature);
            day_2_temperature.setText(tempForecast.getMinTemperature());
            day_2_temperature.append("/");
            day_2_temperature.append(tempForecast.getMaxTemperature());

            TextView day_2_conditions = CL.findViewById(R.id.day_2_conditions);
            day_2_conditions.setText(temp.getCondition());

            ImageView day_2_icon = CL.findViewById(R.id.day_2_icon);
            fc.SetWeatherIcon(day_2_icon, temp.getCondition());

            tempForecast = (ForecastWeather) forecastWeathers.get(2);
            forecastWeatherList.add(2,tempForecast);
            temp = tempForecast.getForecastWeather();

            CV = findViewById(R.id.day_3_card);
            CL = CV.findViewById(R.id.day_3_constraint);

            TextView day_3 = CL.findViewById(R.id.day_3);
            day_3.setText(temp.getDayOfWeek());

            TextView day_3_temperature = CL.findViewById(R.id.day_3_temperature);
            day_3_temperature.setText(tempForecast.getMinTemperature());
            day_3_temperature.append("/");
            day_3_temperature.append(tempForecast.getMaxTemperature());

            TextView day_3_conditions = CL.findViewById(R.id.day_3_conditions);
            day_3_conditions.setText(temp.getCondition());

            ImageView day_3_icon = CL.findViewById(R.id.day_3_icon);
            fc.SetWeatherIcon(day_3_icon, temp.getCondition());




        }
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }
    private void onSwipeRight() {
        Toast.makeText(this, "Swiped Right", Toast.LENGTH_SHORT).show();
        if(i > 0){
            i--;
            recreate();
        }

    }

    private void onSwipeLeft() {
        Toast.makeText(this, "Swiped Left", Toast.LENGTH_SHORT).show();
        if(i < location_id.length-1){
            i++;
            recreate();
        }
    }

    private void onSwipeTop() {
        Toast.makeText(this, "Swiped Top", Toast.LENGTH_SHORT).show();
    }

    private void onSwipeBottom() {
        Toast.makeText(this, "Swiped Bottom", Toast.LENGTH_SHORT).show();
    }



}