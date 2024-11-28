package com.example.rickandmortycompose.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.repository.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {

    private val _charactersStateFlow = MutableStateFlow<List<CharacterResponse>>(emptyList())
    val charactersStateFlow = _charactersStateFlow.asStateFlow()

    fun getAllCharacters() {
        viewModelScope.launch {
            val characters = charactersRepository.getCharacters()
            characters.let {
                _charactersStateFlow.value = it
                Log.e("shamal", "getAllCharacters: $characters")
            }
        }
    }

}