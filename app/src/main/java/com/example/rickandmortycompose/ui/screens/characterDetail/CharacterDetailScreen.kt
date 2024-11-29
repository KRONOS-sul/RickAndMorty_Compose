package com.example.rickandmortycompose.ui.screens.characterDetail

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
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

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    var isClicked by remember { mutableStateOf(false) }
    val buttonHeight by animateDpAsState(
        targetValue = when {
            isClicked && isPressed -> 40.dp
            isClicked -> 12.dp
            else -> 600.dp
        }
    )
    val buttonColor by animateColorAsState(targetValue = if (isClicked) Color.White else Color.Black)
    val textColor by animateColorAsState(targetValue = if (isClicked) Color.Black else Color.White)

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.CenterHorizontally)
        ) {
            GlideImage(
                model = character.image,
                contentDescription = "Character Image",
            )
        }
        Row(modifier = Modifier.padding(4.dp, 8.dp)) {
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(height = buttonHeight),
                colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
                interactionSource = interactionSource,
                onClick = { isClicked = !isClicked }
            ) {
                val text = if (isClicked) "HIDE" else "SHOW"
                Text(text = text, color = textColor)
            }
        }
    }
}