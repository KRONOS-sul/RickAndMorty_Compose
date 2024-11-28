package com.example.rickandmortycompose.ui

import com.example.rickandmortycompose.data.viewmodels.CharactersViewModel
import com.example.rickandmortycompose.data.viewmodels.EpisodeViewModel
import com.example.rickandmortycompose.data.viewmodels.SingleCharacterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { CharactersViewModel(get()) } //Отправляем зависимость repository
    viewModel { EpisodeViewModel(get()) }
    viewModel { SingleCharacterViewModel(get()) }

}