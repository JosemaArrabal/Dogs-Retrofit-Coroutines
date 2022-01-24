package dev.arrabaljosema.dogsretrofitcoroutines

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.arrabaljosema.dogsretrofitcoroutines.databinding.ItemDogBinding

class DogViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDogBinding.bind(view)

    fun bind(image: String) {
        /* Hay que convertir una URL en una imagen (tipo de la función)-> Através de la
        * librería picasso */
        Picasso.get().load(image).into(binding.ivDog)
    }
}