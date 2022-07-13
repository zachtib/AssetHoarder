package com.zachtib.sampleapp.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    Column {
        Text(text = "Welcome to the home screen")
        Button(onClick = { viewModel.onFirstButtonPressed() }) {
            Text(text = "Go to Profile 1")
        }
        Button(onClick = { viewModel.onSecondButtonPressed() }) {
            Text(text = "Go to Profile 2")
        }
        Button(onClick = { viewModel.onSettingsPressed() }) {
            Text(text = "Go to Settings")
        }

        Text("You've clicked ${viewModel.counter} times")
        Button(onClick = { viewModel.onIncrementCounter() }) {
            Text(text = "Click me!")
        }
    }
}
