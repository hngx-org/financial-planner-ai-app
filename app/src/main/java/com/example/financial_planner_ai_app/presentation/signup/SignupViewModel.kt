package com.example.financial_planner_ai_app.presentation.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SignupViewModel: ViewModel() {
    private val _signupUiState = MutableStateFlow(SignupUiState())
    val signupUiState = _signupUiState

    fun updateUserInput(userInput: SignupUiState) {
        _signupUiState.value = userInput
    }
}