package mx.unam

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("message") var images: List<String>
)