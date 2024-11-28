package com.example.rickandmortycompose.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.model.SingleCharacterResponse
import com.example.rickandmortycompose.data.repository.SingleCharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SingleCharacterViewModel(private val repository: SingleCharacterRepository) : ViewModel() {

    private val _characterStateFlow = MutableStateFlow<SingleCharacterResponse?>(null)
    val characterStateFlow = _characterStateFlow.asStateFlow()

    fun getCharacterById(characterId: Int) {
        viewModelScope.launch {
            val character = repository.getCharacter(characterId)
            _characterStateFlow.value = character
            Log.e("shamal", "getCharacterById: $character")
        }
    }

}