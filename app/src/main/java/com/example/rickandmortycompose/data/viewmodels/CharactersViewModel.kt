package com.example.rickandmortycompose.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmortycompose.data.repository.CharactersRepository

class CharactersViewModel(charactersRepository: CharactersRepository) : ViewModel() {

    val charactersPagingFlow = charactersRepository.getCharacters().flow.cachedIn(viewModelScope)

}