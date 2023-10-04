package com.example.financial_planner_ai_app.presentation.homeScreen

sealed interface HomeEvents {
    object OnProfileToggle : HomeEvents
    object OnLogout : HomeEvents
    object OnGenerateChatResponse : HomeEvents
    object OnSaveInteraction : HomeEvents
    data class OnQueryChanged(val query: String) : HomeEvents
}
