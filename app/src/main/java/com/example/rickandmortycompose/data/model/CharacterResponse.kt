package com.example.rickandmortycompose.data.model

import com.google.gson.annotations.SerializedName

data class CharactersResultResponse(
    @SerializedName("info")
    val pageInfo: PageInfo,
    @SerializedName("results")
    val charactersResults: List<CharacterResponse>,
)

data class PageInfo(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?,
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
