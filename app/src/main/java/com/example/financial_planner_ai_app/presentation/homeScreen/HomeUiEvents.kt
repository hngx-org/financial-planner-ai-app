package com.example.financial_planner_ai_app.presentation.homeScreen

sealed interface HomeUiEvents {
    object LogOut : HomeUiEvents

    data class ShowSnackbar(val message: String) : HomeUiEvents
}