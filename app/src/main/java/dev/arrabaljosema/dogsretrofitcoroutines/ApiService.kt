package dev.arrabaljosema.dogsretrofitcoroutines

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/* La función de la interface es crear el método por cual accedemos
 a consumir la API*/
interface ApiService {
    @GET
    fun getDogsByBreed(@Url url: String): Response<DogsResponse>
}