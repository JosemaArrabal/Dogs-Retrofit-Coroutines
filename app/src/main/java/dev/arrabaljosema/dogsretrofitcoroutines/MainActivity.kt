package dev.arrabaljosema.dogsretrofitcoroutines

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import dev.arrabaljosema.dogsretrofitcoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svDogs.setOnQueryTextListener(this)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        /* Se necesita la var adapter e igualarla al adapter pasándole una lista,
        * ahora en el searchByName, cuando se recupere el listado de imágenes se le empiezan a meter */
        adapter = DogAdapter(dogImages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
    }

    /* Se crea instancia del objeto retrofit. Va a tener url original, conversor json a este
    * modelo de datos y toda la config para hacer llamadas a internet */

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /* Corrutinas */
    @SuppressLint("NotifyDataSetChanged")
    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit()
                    .create(ApiService::class.java)
                    .getDogsByBreed("$query/images")
                val puppies = call.body()
                runOnUiThread {
                    if (call.isSuccessful) {
                        val images = puppies?.message ?: emptyList()
                        dogImages.clear()
                        dogImages.addAll(images)
                        adapter.notifyDataSetChanged()
                    } else {
                        showError()
                    }
                }
            } catch (e: Exception) {
                Log.e("", "CASCO POR ESTO : ${e.message}")
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Fallo en la comunicación", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}