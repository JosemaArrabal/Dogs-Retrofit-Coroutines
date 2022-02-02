package dev.arrabaljosema.dogsretrofitcoroutines


/* Con la anotación @SerializedName, se puede cambiar de nombre al parámetro recibido.*/
data class DogsResponse(
    var status: String,
    var message: List<String>
)