package mx.unam

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getBreedByDogs(@Url url: String): Response<DogResponse>
}