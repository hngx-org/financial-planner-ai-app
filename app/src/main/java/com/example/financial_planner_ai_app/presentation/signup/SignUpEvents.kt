package com.example.financial_planner_ai_app.presentation.signup

sealed interface SignUpEvents {

    data class OnUsernameChanged(val name: String) : SignUpEvents
    data class OnEmailChanged(val email: String) : SignUpEvents
    data class OnPasswordChanged(val password: String) : SignUpEvents
    data class OnConfirmPasswordChanged(val confirmPassword: String) : SignUpEvents
    object OnSignUpClicked : SignUpEvents
    object OnLoginClicked : SignUpEvents
    object OnPasswordVisibilityClicked : SignUpEvents

}