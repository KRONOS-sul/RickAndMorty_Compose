package com.example.rickandmortycompose.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortycompose.R
import com.example.rickandmortycompose.ui.screens.Screens
import com.example.rickandmortycompose.ui.screens.character.CharacterScreen
import com.example.rickandmortycompose.ui.screens.characterDetail.CharacterDetailScreen
import com.example.rickandmortycompose.ui.screens.episode.EpisodeScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Design()
        }
    }
}

@Composable
fun Design() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar(navController) },
        bottomBar = { BottomBar(navController) }) { innerPadding ->

        NavHost(
            navController,
            startDestination = Screens.Character.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.Character.route) { CharacterScreen(navController = navController) }
            composable(Screens.Episode.route) { EpisodeScreen() }
            composable(Screens.DetailCharacter.route) { backStackEntry ->
                val characterId = backStackEntry.arguments?.getString("characterId")
                    ?.toInt()   //получение аргумента из предыдущего экрана
                if (characterId != null) {
                    CharacterDetailScreen(characterId = characterId)
                }
            }
        }
    }
}

@Composable
fun TopBar(navController: NavHostController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    val screenName = when (currentDestination) {
        Screens.Character.route -> Screens.Character.title
        Screens.Episode.route -> Screens.Episode.title
        else -> ""
    }
    val fonts = FontFamily(
        Font(R.font.oswald_medium, FontWeight.Medium),
        Font(R.font.oswald_regular, FontWeight.Normal),
        Font(R.font.oswald_light, FontWeight.Light)
    )
    TopAppBar(
        title = {},
        actions = {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {

                Text(
                    text = screenName,
                    style = TextStyle(fontFamily = fonts, fontWeight = FontWeight.Medium),
                    fontSize = 24.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }, backgroundColor = Color.White
    )
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(Screens.Character, Screens.Episode)

    BottomAppBar {
        items.forEach { screen ->   // Цикл который повторит код для каждого экрана
            val currentDestination = navController.currentBackStackEntry?.destination?.route

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(screen.icon),
                        contentDescription = (screen.title)
                    )
                },
                label = { Text(screen.title) },
                selected = currentDestination == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true // Сохранение состояния экрана при переходе
                        }
                        launchSingleTop =
                            true  // Защита от создания нескольких одинаковых экранов в стек навигации
                        restoreState =
                            true     // Восстановление состояния экрана при переходе
                    }
                }
            )
        }
    }
}