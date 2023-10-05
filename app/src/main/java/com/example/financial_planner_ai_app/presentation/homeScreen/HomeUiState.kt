package com.example.financial_planner_ai_app.presentation.homeScreen

import com.shegs.hng_auth_library.model.UserData


data class HomeUiState(
    val userData: UserData = UserData(
        created_at = "N/A",
        credit = 0,
        email = "N/A",
        id = "N/A",
        name = "N/A",
        updated_at = "N/A"
    ),
    val showUserProfile: Boolean = false,
    val showInteractionCard: Boolean = false,
    val prompt: String = "",
    val aiResponse: String = "",
    val interactionTitle: String = "",
    val loading: Boolean = false,
    val userId: String = ""
)
