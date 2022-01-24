package dev.arrabaljosema.dogsretrofitcoroutines

import com.google.gson.annotations.SerializedName

/* Con la anotación @SerializedName, se puede cambiar de nombre al parámetro recibido.*/
data class DogsResponse(
    @SerializedName("status") var status: String,
    @SerializedName("message") var images: List<String>
)