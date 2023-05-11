package com.example.weather

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class WeeklyForecastActivity : AppCompatActivity() {
    private lateinit var day1TV : TextView
    private lateinit var day2TV : TextView
    private lateinit var day3TV : TextView
    private lateinit var day4TV : TextView
    private lateinit var day5TV : TextView
    private lateinit var day1IV : ImageView
    private lateinit var day2IV : ImageView
    private lateinit var day3IV : ImageView
    private lateinit var day4IV : ImageView
    private lateinit var day5IV : ImageView
    private lateinit var bitmap : Bitmap
    private  var date : Date = Date()
    private var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weekly_forecast)
        day1TV = findViewById(R.id.day1)
        day2TV = findViewById(R.id.day2)
        day3TV = findViewById(R.id.day3)
        day4TV = findViewById(R.id.day4)
        day5TV = findViewById(R.id.day5)
        day1IV = findViewById(R.id.image_day1)
        day2IV = findViewById(R.id.image_day2)
        day3IV = findViewById(R.id.image_day3)
        day4IV = findViewById(R.id.image_day4)
        day5IV = findViewById(R.id.image_day5)
        var weatherArray = ArrayList<Weather.WeatherInformation?>()
        weatherArray.add(MainActivity.weather.getWeeklyInfo(0))
        weatherArray.add(MainActivity.weather.getWeeklyInfo(1))
        weatherArray.add(MainActivity.weather.getWeeklyInfo(2))
        weatherArray.add(MainActivity.weather.getWeeklyInfo(3))
        weatherArray.add(MainActivity.weather.getWeeklyInfo(4))
        setForeCast(weatherArray)
        setImage(weatherArray)



    }

    fun setForeCast(weekForeCast : ArrayList<Weather.WeatherInformation?>) {
        calendar.setTime(date)

        if (weekForeCast != null) {
            for (i in 0..4) {
                var currDay = calendar.time
                var sdf = SimpleDateFormat("MM-dd")
                var display = sdf.format((currDay)) + ": "
                display += weekForeCast[i]!!.mainWeather + "\n"
                display += "High: "+ MainActivity.weather.
                convertKelvinToFahrenheit(weekForeCast[i]!!.tempMaxKelvin)
                display += " F Low: " +
                        MainActivity.weather.
                        convertKelvinToFahrenheit(weekForeCast[i]!!.tempMinKelvin) + " F"
                if (i == 0) {
                    day1TV.text = display
                }else if (i == 1) {
                    day2TV.text = display
                }else if (i == 2) {
                    day3TV.text = display
                }else if (i == 3) {
                    day4TV.text = display
                }else {
                    day5TV.text = display

                }
                calendar.add(Calendar.DATE, 1)
            }
        }

    }
    fun setImage(weekForeCast : ArrayList<Weather.WeatherInformation?>) {
        if (weekForeCast != null) {
            for (i in 0..4) {
                if (weekForeCast[i]!!.mainWeather.contains("Rain")) {
                    bitmap = BitmapFactory.decodeResource(resources, R.raw.rain)
                } else if (weekForeCast[i]!!.mainWeather.contains("Partly")) {
                    bitmap = BitmapFactory.decodeResource(resources, R.raw.partly_cloudy)
                } else if (weekForeCast[i]!!.mainWeather.contains("Sunny")) {
                    bitmap = BitmapFactory.decodeResource(resources, R.raw.sunny)
                } else if (weekForeCast[i]!!.mainWeather.contains("Clouds")) {
                    bitmap = BitmapFactory.decodeResource(resources, R.raw.cloudy)
                } else if (weekForeCast[i]!!.mainWeather.contains("Clear")) {
                    bitmap = BitmapFactory.decodeResource(resources, R.raw.sunny)
                }else if (weekForeCast[i]!!.mainWeather.contains("Snow")){
                    bitmap = BitmapFactory.decodeResource(resources, R.raw.snow)
                }else if (weekForeCast[i]!!.mainWeather.contains("Fog")){
                    bitmap = BitmapFactory.decodeResource(resources, R.raw.fog)
                }else if (weekForeCast[i]!!.mainWeather.contains("Thunderstorm")){
                    bitmap = BitmapFactory.decodeResource(resources, R.raw.thunderstorm)
                }else if (weekForeCast[i]!!.mainWeather.contains("Windy")){
                    bitmap = BitmapFactory.decodeResource(resources, R.raw.windy)
                }else {
                    bitmap = BitmapFactory.decodeResource(resources, R.raw.na)
                }
                if (i == 0) {
                    day1IV.setImageBitmap(bitmap)
                }else if (i == 1) {
                    day2IV.setImageBitmap(bitmap)
                }else if (i == 2) {
                    day3IV.setImageBitmap(bitmap)
                }else if (i == 3) {
                    day4IV.setImageBitmap(bitmap)
                }else {
                    day5IV.setImageBitmap(bitmap)

                }
            }

        }
    }

    fun goBack(v: View) {
        finish()
        overridePendingTransition(R.anim.slide_from_left, 0)
    }
}
