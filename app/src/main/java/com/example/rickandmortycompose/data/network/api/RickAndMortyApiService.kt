package com.example.rickandmortycompose.data.network.api

import com.example.rickandmortycompose.data.model.CharactersResultResponse
import com.example.rickandmortycompose.data.model.EpisodesResultResponse
import com.example.rickandmortycompose.data.model.SingleCharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {

    @GET("character")
    suspend fun getAllCharacters( @Query("page") page: Int): Response<CharactersResultResponse>

    @GET("episode")
    suspend fun getAllEpisodes(): Response<EpisodesResultResponse>

    @GET("character/{id}")
    suspend fun getSingleCharacter(@Path("id") characterId: Int): Response<SingleCharacterResponse>
}