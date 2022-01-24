package dev.arrabaljosema.dogsretrofitcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.arrabaljosema.dogsretrofitcoroutines.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /* Se crea instancia del objeto retrofit. Va a tener url original, conversor json a este
    * modelo de datos y toda la config para hacer llamadas a internet.*/

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breeds/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}