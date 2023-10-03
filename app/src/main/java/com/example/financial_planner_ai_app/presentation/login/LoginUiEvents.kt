package com.example.financial_planner_ai_app.presentation.login

sealed interface LoginUiEvents {
    data class ShowSnackBar(val message: String): LoginUiEvents
    object NavigateToHome: LoginUiEvents
    object NavigateToSignup: LoginUiEvents
}