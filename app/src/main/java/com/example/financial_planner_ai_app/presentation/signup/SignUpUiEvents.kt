package com.example.financial_planner_ai_app.presentation.signup

sealed interface SignUpUiEvents {
    object NavigateToLogin : SignUpUiEvents
    data class ShowSnackbar(val message: String) : SignUpUiEvents
}