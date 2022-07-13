package com.zachtib.sampleapp.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
) {
    Column {
        Text(text = viewModel.message)
        Button(onClick = { viewModel.onGoBackPressed() }) {
            Text(text = "Go back")
        }
    }
}
