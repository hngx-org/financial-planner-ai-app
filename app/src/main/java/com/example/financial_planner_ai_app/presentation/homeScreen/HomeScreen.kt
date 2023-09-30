package com.example.financial_planner_ai_app.presentation.homeScreen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel
) {
    val homeScreenUiState by homeScreenViewModel.homeScreenUiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hey Eliud,")
        Text(text = "Welcome back to Finance Planner App")
        OutlinedTextField(
            value = homeScreenUiState.searchText,
            onValueChange = {
                homeScreenViewModel.updateSearchPrompt(homeScreenUiState.copy(searchText = it))
            }
        )
    }
}