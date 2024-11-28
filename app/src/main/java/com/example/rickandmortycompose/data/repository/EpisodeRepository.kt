package com.example.rickandmortycompose.data.repository

import com.example.rickandmortycompose.data.model.EpisodeResponse
import com.example.rickandmortycompose.data.network.api.RickAndMortyApiService

class EpisodeRepository(private val rickAndMortyApiService: RickAndMortyApiService) {

    suspend fun getEpisodes(): List<EpisodeResponse> {
        val response = rickAndMortyApiService.getAllEpisodes()

        return if (response.isSuccessful) {
            response.body()?.episodesResponse ?: emptyList()
        } else {
            emptyList()
        }
    }
}