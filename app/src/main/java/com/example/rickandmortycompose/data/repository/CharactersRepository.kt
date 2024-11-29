package com.example.rickandmortycompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.network.api.RickAndMortyApiService

class CharactersRepository(private val rickAndMortyApiService: RickAndMortyApiService) {

    fun getCharacters(): Pager<Int, CharacterResponse> {
//        val response = rickAndMortyApiService.getAllCharacters()   //retrofit делает запрос
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(rickAndMortyApiService)
            }
        )

//        return if (response.isSuccessful) {
//            response.body()?.charactersResults ?: emptyList()  //получаем список персонажей
//        } else {
//            emptyList()
//        }
    }
}