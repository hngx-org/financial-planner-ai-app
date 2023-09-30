package com.example.financial_planner_ai_app.presentation.homeScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenViewModel: ViewModel() {
    private val _homeScreenUiState = MutableStateFlow(HomeScreenUiState())
    val homeScreenUiState = _homeScreenUiState

    fun updateSearchPrompt(userInput: HomeScreenUiState) {
        _homeScreenUiState.value = userInput
    }
}