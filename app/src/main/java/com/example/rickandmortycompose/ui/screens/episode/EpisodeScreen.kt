package com.example.rickandmortycompose.ui.screens.episode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.data.model.EpisodeResponse
import com.example.rickandmortycompose.data.viewmodels.EpisodeViewModel
import com.example.rickandmortycompose.ui.theme.Ink
import org.koin.androidx.compose.koinViewModel

@Composable
fun EpisodeScreen(viewModel: EpisodeViewModel = koinViewModel()) {

    val episode by viewModel.episodesStateFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllEpisodes()
    }
    EpisodeList(episodes = episode)
}

@Composable
fun EpisodeList(episodes: List<EpisodeResponse>) {
    LazyColumn(
        modifier = Modifier
            .background(Ink)
            .padding(16.dp)
    ) {
        items(episodes.size) {
            EpisodeItem(episode = episodes[it])
        }
    }
}

@Composable
fun EpisodeItem(episode: EpisodeResponse) {
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
            .padding(8.dp)
    ) {
        Row {
            Text(
                modifier = Modifier.padding(0.dp, 8.dp),
                text = episode.episode,
                style = TextStyle(
                    fontFamily = fonts,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = Color.White,
                )
            )
            Spacer(modifier = Modifier.padding(8.dp, 0.dp))
            Column {
                Text(
                    text = episode.name,
                    style = TextStyle(
                        fontFamily = fonts,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = episode.airDate,
                    style = TextStyle(
                        fontFamily = fonts,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                    ),
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(0.dp, 12.dp))
}