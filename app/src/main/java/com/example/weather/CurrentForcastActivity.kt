package com.example.weather

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CurrentForcastActivity : AppCompatActivity() {
    private lateinit var currWeatherIV : ImageView
    private lateinit var currWeatherTV : TextView
    private lateinit var bitmap : Bitmap
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_forecast)
        currWeatherIV = findViewById(R.id.curr_weather_image)
        currWeatherTV = findViewById(R.id.curr_weather)
        val currForecast = MainActivity.weather.getDailyInfo()
        setImage(currForecast.mainWeather)
        setTextView(currForecast)
    }

    //sets the image for the view
    //Maybe theres a better way to do this not sure?
    fun setImage(weather : String) {
        if (weather.contains("Rain")) {
            bitmap = BitmapFactory.decodeResource(resources, R.raw.rain)
        }else if(weather.contains("Partly")) {
            bitmap = BitmapFactory.decodeResource(resources, R.raw.partly_cloudy)

        }else if(weather.contains("Sunny")) {
            bitmap = BitmapFactory.decodeResource(resources, R.raw.sunny)

        }else if(weather.contains("Clouds")) {
            bitmap = BitmapFactory.decodeResource(resources, R.raw.cloudy)
        }else if(weather.contains("Clear")) {
            bitmap = BitmapFactory.decodeResource(resources, R.raw.sunny)
        }else {
            bitmap = BitmapFactory.decodeResource(resources,R.raw.sunny)
        }
        currWeatherIV.setImageBitmap(bitmap)
    }
    fun setTextView(forecast: Weather.WeatherInformation) {
        var display =  forecast.name + ", " + forecast.country + "\n"
        display += forecast.mainWeather + "\n Temp: " + forecast.temperatureKelvin + "\n"
        display += "Wind Speed: " + forecast.windSpeed + " mph\n"
        display +=  "Humidity: " + forecast.humidity + "%"
        currWeatherTV.text = display
    }
    fun goBack(v: View) {
        finish()
        overridePendingTransition(R.anim.fade_in_and_scale, 0)
    }
}
