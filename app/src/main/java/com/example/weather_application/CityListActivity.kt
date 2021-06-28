package com.example.weather_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CityListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_list)
    }

    fun cityOnClickOmsk(view: View) {
        val cityName = "Омск"
        val cityId = "1496153"
        cityOnClick(cityName, cityId)
    }

    fun cityOnClickNov(view: View) {
        val cityName = "Новосибирск"
        val cityId = "1496747"
        cityOnClick(cityName, cityId)
    }

    fun cityOnClick(cityName: String, cityId: String) {
        val cityIntent = Intent(this, MainActivity::class.java)
        cityIntent.putExtra(MainActivity.TOTAL_COUNT1, cityName)
        cityIntent.putExtra(MainActivity.TOTAL_COUNT2, cityId)

        startActivity(cityIntent)
    }
}