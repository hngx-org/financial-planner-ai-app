package com.example.financial_planner_ai_app.presentation.signup

data class SignUpState(
    val username: String = "",
    val usernameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val loading: Boolean = false,
    val passwordVisibility: Boolean = false
)
