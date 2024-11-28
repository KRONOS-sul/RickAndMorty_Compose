package com.example.rickandmortycompose.ui.screens.characterDetail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rickandmortycompose.data.model.SingleCharacterResponse
import com.example.rickandmortycompose.data.viewmodels.SingleCharacterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetailScreen(characterId: Int, viewModel: SingleCharacterViewModel = koinViewModel()) {

    val character by viewModel.characterStateFlow.collectAsState()

    LaunchedEffect(characterId) {
        viewModel.getCharacterById(characterId)
    }
    character?.let {
        CharacterDetail(character = it)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterDetail(character: SingleCharacterResponse) {
    GlideImage(
        modifier = Modifier.fillMaxWidth(),
        model = character.image,
        contentDescription = "Character Image"
    )
    Text(text = character.name)
}