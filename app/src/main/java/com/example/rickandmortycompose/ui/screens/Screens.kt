package com.example.rickandmortycompose.ui.screens

import com.example.rickandmortycompose.R


sealed class Screens(val route: String, val title: String, val icon: Int) {

    data object Character : Screens("character", "Character", R.drawable.ic_person)
    data object Episode : Screens("episode", "Episode", R.drawable.ic_episode)
    data object DetailCharacter : Screens("detail/{characterId}", "Detail", R.drawable.ic_list)

}