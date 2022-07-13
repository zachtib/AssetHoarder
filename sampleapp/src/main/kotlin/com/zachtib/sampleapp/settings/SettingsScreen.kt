package com.zachtib.sampleapp.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
) {
    Column {
        Text(text = "Welcome to the settings screen")
        Button(onClick = { viewModel.onGoBackPressed() }) {
            Text(text = "Go back")
        }
    }
}
