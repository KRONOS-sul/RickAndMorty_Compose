package com.example.rickandmortycompose.data.model

import com.google.gson.annotations.SerializedName

data class CharactersResultResponse(
    @SerializedName("results")
    val charactersResponse: List<CharacterResponse>,
)

data class CharacterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("location")
    val location: Location,

)

data class Location(
    @SerializedName("name")
    val name: String,
)
