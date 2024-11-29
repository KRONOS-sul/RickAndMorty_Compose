package com.example.rickandmortycompose.data.repository

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.network.api.RickAndMortyApiService

class CharacterPagingSource(private val apiService: RickAndMortyApiService) :
    PagingSource<Int, CharacterResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        return try {
            val currentPage = params.key ?: 1

            val response = apiService.getAllCharacters(currentPage)
            val characters = response.body()?.charactersResults ?: emptyList()

            val nextKey =
                response.body()?.pageInfo?.next?.let { nextPage ->   //(characters.isEmpty()) null else currentPage + 1
                    Uri.parse(nextPage).getQueryParameter("page")?.toInt()
                }
            val prevKey = if (currentPage == 1) null else currentPage - 1

            LoadResult.Page(characters, nextKey = nextKey, prevKey = prevKey)

        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? {
        TODO("Not yet implemented")
    }
}