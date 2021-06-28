package com.example.weather_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

const val BASE_URL = "http://api.openweathermap.org/"

class MainActivity : AppCompatActivity() {

    private var id = "1496153"
    private val appid = "09afb936aab7d7df625db9a73c422f21"
    private val units = "metric"
    private val lang = "ru"

    companion object {
        const val TOTAL_COUNT1 = "total_count1"
        const val TOTAL_COUNT2 = "total_count2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textCityView = findViewById<TextView>(R.id.textCity)
        val textTemperatureView = findViewById<TextView>(R.id.textTemperature)
        val textWeatherView = findViewById<TextView>(R.id.textWeather)

        val reload = findViewById<Button>(R.id.reload)

        getCurrentData(textCityView, textTemperatureView, textWeatherView)
        reload.setOnClickListener{
            getCurrentData(textCityView, textTemperatureView, textWeatherView)
        }
    }

    private fun getCurrentData(textCityView: TextView, textTemperatureView: TextView, textWeatherView: TextView) {

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val countName = intent.getStringExtra(TOTAL_COUNT1)
            val countId = intent.getStringExtra(TOTAL_COUNT2)

            if (countName != null) textCityView.text = countName

                if (countId != null) id = countId
                val response = api.getWeather(id, appid, units, lang).awaitResponse()
                try {
                    if (response.isSuccessful) {

                        val weatherText = response.body()
                        val mainText = weatherText!!.main
                        val weatherSetText = weatherText!!.weather

                        textTemperatureView.text = mainText.temp.toString()
                        textWeatherView.text = weatherSetText.last().description
                    }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Город изменён",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun chooseOnClick (view: View) {
        val chooseIntent = Intent(this, CityListActivity::class.java)
        startActivity(chooseIntent)
    }
}