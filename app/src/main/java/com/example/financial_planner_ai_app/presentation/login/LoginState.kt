package com.example.financial_planner_ai_app.ui.login_screen

data class LoginState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val loading: Boolean = false,
    val passwordVisibility: Boolean = false
)
