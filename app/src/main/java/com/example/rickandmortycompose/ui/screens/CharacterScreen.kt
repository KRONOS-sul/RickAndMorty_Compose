package com.example.rickandmortycompose.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CharacterScreen() {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(20) {
            Text(text = "Yooo $it")
        }
    }
}