/**
 *
 * @author Mangaliso Linda Dlamini S2110978
 */
package com.example.dlamini_mangaliso_s2110978;

import android.os.AsyncTask;
import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class XMLParserHandler extends AsyncTask<String, Void, Object> {

    private XMLParserListener listener;

    public XMLParserHandler(XMLParserListener listener) {
        this.listener = listener;
    }

    private static final String TAG = "XMLParserHandler";

    @Override
    protected Object doInBackground(String... params) {
        String locationID = params[0];
        String urlStr = params[1];

        if (urlStr.contains("observation")) {
            return parseCurrentObservation(locationID, urlStr);
        } else if (urlStr.contains("forecast")) {
            return parseThreeForecast(locationID, urlStr);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        if (listener != null) {
            listener.onResult(result);
        }
    }

    private CurrentObservation parseCurrentObservation(String locationID, String urlStr) {
        CurrentObservation currentObservation = new CurrentObservation();
        Weather weather = new Weather();

        String string = "";
        Weather currOb = new Weather();

        try {
            URL url = new URL(urlStr);
            InputStream inputStream = url.openStream();

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(inputStream, null);

                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String tagName = parser.getName();
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            System.out.println("Start document");
                            break;

                        case XmlPullParser.START_TAG:
                            if(tagName.equalsIgnoreCase("item")){
                                //weather = new Weather();
                                System.out.println("Item found");
                            }
                            break;
                        case XmlPullParser.TEXT:
                            string = parser.getText();
                            break;
                        case XmlPullParser.END_TAG:
                            if(tagName.equalsIgnoreCase("item")){

                            } else if (tagName.equalsIgnoreCase("title")) {
                                //urrOb.setDayOfWeek(title[0]);
                                // weather.setTemperature(title[6]);
                                String[] title = string.split(" ");
                                currOb.setDayOfWeek(title[0]);
                                //currOb.setTemperature(title[6]);
                                //System.out.println(title[0]);
                                //System.out.println(title[6]);

                                String temp = StringUtils.substringAfter(string, "T: ");
                                String[] tempArr = temp.split(",");

                                currOb.setCondition(tempArr[0]);

                            }else if(tagName.equalsIgnoreCase("description")){
                                String temp;
                                String[] tempArr;
                                temp = StringUtils.substringAfter(string, "Temperature: ");
                                tempArr =temp.split(",");
                                currOb.setTemperature(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "Humidity: ");
                                tempArr =temp.split(",");
                                currOb.setHumidity(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "Pressure: ");
                                tempArr =temp.split(",");
                                currOb.setPressure(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "Wind Direction: ");
                                tempArr =temp.split(",");
                                currOb.setWindDirection(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "Wind Speed: ");
                                tempArr =temp.split(",");
                                currOb.setWindSpeed(tempArr[0]);
                            }else if(tagName.equalsIgnoreCase("dc:date")){
                                String[] date = string.split(" ");
                                currOb.setDate(string);
                            }
                            break;

                    }
                    eventType = parser.next();
                }
            } catch (XmlPullParserException | IOException e) {
                Log.e(TAG, "Error parsing XML", e);
            } finally {
                inputStream.close();
            }

        } catch (IOException e) {
            Log.e(TAG, "Error opening connection", e);
        }
        currentObservation.setCurrentWeather(currOb);

        return currentObservation;
    }

    private List<ForecastWeather> parseThreeForecast(String locationID, String urlStr) {
        List<ForecastWeather> forecastWeatherList = new ArrayList<>();

        String string="";
        Weather currOb = new Weather();
        ForecastWeather fw = new ForecastWeather();
        List<ForecastWeather> TDF = new ArrayList<>();


        try {
            URL url = new URL(urlStr);
            InputStream inputStream = url.openStream();

            try{
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                //factory.setNamespaceAware(true);
                XmlPullParser  parser = factory.newPullParser();
                parser.setInput(inputStream, null);
                int eventType = parser.getEventType();
                while(eventType != XmlPullParser.END_DOCUMENT){
                    String tagName = parser.getName();
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            if(tagName.equalsIgnoreCase("item")){
                                //weather = new Weather();
                                //System.out.println("Item found");
                                currOb = new Weather();
                                fw = new ForecastWeather();
                            }
                            break;
                        case XmlPullParser.TEXT:
                            string = parser.getText();
                            break;
                        case XmlPullParser.END_TAG:
                            if(tagName.equalsIgnoreCase("item")){
                                fw.setForecastWeather(currOb);
                                TDF.add(fw);

                            } else if (tagName.equalsIgnoreCase("title")) {
                                String[] title = string.split(":");
                                currOb.setDayOfWeek(title[0]);

                                String temp = StringUtils.substringAfter(string, ": ");
                                String[] tempArr = temp.split(",");

                                currOb.setCondition(tempArr[0]);

                            }else if(tagName.equalsIgnoreCase("description")){
                                String temp;
                                String[] tempArr;
                                temp = StringUtils.substringAfter(string, "Humidity: ");
                                tempArr =temp.split(",");
                                currOb.setHumidity(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "Pressure: ");
                                tempArr =temp.split(",");
                                currOb.setPressure(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "Wind Direction: ");
                                tempArr =temp.split(",");
                                currOb.setWindDirection(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "Wind Speed: ");
                                tempArr =temp.split(",");
                                currOb.setWindSpeed(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "Minimum Temperature: ");
                                tempArr =temp.split(" ");
                                fw.setMinTemperature(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "Maximum Temperature: ");
                                tempArr =temp.split(" ");
                                fw.setMaxTemperature(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "Sunrise: ");
                                tempArr =temp.split(" ");
                                fw.setSunrise(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "Sunset: ");
                                tempArr =temp.split(" ");
                                fw.setSunset(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "Visibility: ");
                                tempArr =temp.split(",");
                                fw.setVisibility(tempArr[0]);

                                temp = StringUtils.substringAfter(string, "UV Risk: ");
                                tempArr =temp.split(",");
                                fw.setUv_risk(tempArr[0]);



                            }else if(tagName.equalsIgnoreCase("dc:date")){
                                String[] date = string.split(" ");
                                currOb.setDate(string);
                            }
                            break;


                    }
                    eventType = parser.next();
                }

            }catch (XmlPullParserException e) {e.printStackTrace();}

        } catch (IOException e) {
            Log.e(TAG, "Error opening connection", e);
        }
        // Similar parsing logic as parseCurrentObservation method

        return TDF;
    }
}
