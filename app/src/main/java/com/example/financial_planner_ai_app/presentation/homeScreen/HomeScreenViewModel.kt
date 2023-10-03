package com.example.financial_planner_ai_app.presentation.homeScreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(): ViewModel() {
    private val _homeScreenUiState = MutableStateFlow(HomeScreenUiState())
    val homeScreenUiState = _homeScreenUiState

    fun updateSearchPrompt(userInput: HomeScreenUiState) {
        _homeScreenUiState.value = userInput
    }
}