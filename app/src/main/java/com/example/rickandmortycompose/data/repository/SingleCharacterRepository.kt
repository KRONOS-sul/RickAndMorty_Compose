package com.example.rickandmortycompose.data.repository

import com.example.rickandmortycompose.data.model.SingleCharacterResponse
import com.example.rickandmortycompose.data.network.api.RickAndMortyApiService

class SingleCharacterRepository(private val rickAndMortyApiService: RickAndMortyApiService) {

    suspend fun getCharacter(characterId: Int): SingleCharacterResponse {
        val response = rickAndMortyApiService.getSingleCharacter(characterId)

        return if (response.isSuccessful) {
            response.body() ?: throw Exception("Failed to fetch character details")
        } else {
            throw Exception("Failed to fetch character details")
        }
    }

}