package com.example.rickandmortycompose.ui.screens.character

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.data.model.CharacterResponse
import com.example.rickandmortycompose.data.viewmodels.CharactersViewModel
import com.example.rickandmortycompose.ui.theme.Ink
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterScreen(
    navController: NavController,
    viewModel: CharactersViewModel = koinViewModel(),  //koinViewModel() - для создания объекта CharacterViewModel
) {

    val characters by viewModel.charactersStateFlow.collectAsState()
    //collectAsState() - для обновления экрана при изменении данных

    LaunchedEffect(Unit) {  //LaunchedEffect - для запуска один раз при создании экрана
        viewModel.getAllCharacters()
    }
    CharacterList(characters = characters, navController = navController)
}

@Composable
fun CharacterList(characters: List<CharacterResponse>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .background(Ink)
            .padding(16.dp)
    ) {
        items(characters.size) {
            CharacterItem(
                characters = characters[it],
                onClick = {
                    navController.navigate("detail/${characters[it].id}")
                })
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(characters: CharacterResponse, onClick: () -> Unit) {
    val fonts = FontFamily(
        Font(R.font.oswald_medium, FontWeight.Medium),
        Font(R.font.oswald_regular, FontWeight.Normal),
        Font(R.font.oswald_light, FontWeight.Light)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.DarkGray)
            .clickable { onClick() }
    ) {
        Row {
            GlideImage(
                model = characters.image,
                contentDescription = "Character Image"
            )
            Log.d("shamal", "Image URL: ${characters.image}")
            Spacer(modifier = Modifier.padding(12.dp, 0.dp))
            Column {
                Text(
                    text = characters.name,
                    style = TextStyle(
                        fontFamily = fonts,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val statusColor = when (characters.status) {
                        "Alive" -> Color.Green
                        "Dead" -> Color.Red
                        else -> Color.Gray
                    }
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(statusColor, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = characters.status.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                    Text(
                        text = " - " + characters.species,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(0.dp, 8.dp))
                Text(text = "Last known location:", color = Color.LightGray)
                Text(
                    text = characters.location.name
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(0.dp, 12.dp))
}