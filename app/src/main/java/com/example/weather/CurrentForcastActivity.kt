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
        } else if (weather.contains("Partly")) {
            bitmap = BitmapFactory.decodeResource(resources, R.raw.partly_cloudy)
        } else if (weather.contains("Sunny")) {
            bitmap = BitmapFactory.decodeResource(resources, R.raw.sunny)
        } else if (weather.contains("Clouds")) {
            bitmap = BitmapFactory.decodeResource(resources, R.raw.cloudy)
        } else if (weather.contains("Clear")) {
            bitmap = BitmapFactory.decodeResource(resources, R.raw.sunny)
        }else if (weather.contains("Snow")){
            bitmap = BitmapFactory.decodeResource(resources, R.raw.snow)
        }else if (weather.contains("Fog")){
            bitmap = BitmapFactory.decodeResource(resources, R.raw.fog)
        }else if (weather.contains("Thunderstorm")){
            bitmap = BitmapFactory.decodeResource(resources, R.raw.thunderstorm)
        }else if (weather.contains("Windy")){
            bitmap = BitmapFactory.decodeResource(resources, R.raw.windy)
        }else {
            bitmap = BitmapFactory.decodeResource(resources, R.raw.na)
        }
        currWeatherIV.setImageBitmap(bitmap)
    }
    fun setTextView(forecast: Weather.WeatherInformation) {
        var display =  forecast.name + ", " + forecast.country + "\n"
        display += forecast.mainWeather + "\n Temp: " +
                MainActivity.weather.convertKelvinToFahrenheit(forecast.temperatureKelvin) + " F\n"
        display += "Wind Speed: " + forecast.windSpeed + " mph\n"
        display +=  "Humidity: " + forecast.humidity + "%"
        currWeatherTV.text = display
    }
    fun goBack(v: View) {
        finish()
        overridePendingTransition(R.anim.fade_in_and_scale, 0)
    }
}
