package com.example.rickandmortycompose.data.repository

import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.network.api.RickAndMortyApiService

class CharactersRepository(private val rickAndMortyApiService: RickAndMortyApiService) {

    suspend fun getCharacters(): List<CharacterResponse> {
        val response = rickAndMortyApiService.getAllCharacters()   //retrofit делает запрос

        return if (response.isSuccessful) {
            response.body()?.charactersResponse ?: emptyList()  //получаем список персонажей
        } else {
            emptyList()
        }
    }
}