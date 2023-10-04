package com.example.financial_planner_ai_app.presentation.history

sealed interface HistoryUiEvents {
    data class ShowSnackbar(val message: String) : HistoryUiEvents
}