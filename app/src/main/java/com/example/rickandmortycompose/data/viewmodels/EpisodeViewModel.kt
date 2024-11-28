package com.example.rickandmortycompose.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.model.EpisodeResponse
import com.example.rickandmortycompose.data.repository.EpisodeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EpisodeViewModel(private val episodeRepository: EpisodeRepository) : ViewModel() {

    private val _episodesStateFlow = MutableStateFlow<List<EpisodeResponse>>(emptyList())
    val episodesStateFlow = _episodesStateFlow.asStateFlow()

    fun getAllEpisodes() {
        viewModelScope.launch {
            val episodes = episodeRepository.getEpisodes()
            episodes.let {
                _episodesStateFlow.value = it
                Log.e("shamal", "getAllEpisodes: $episodes ")
            }
        }
    }

}