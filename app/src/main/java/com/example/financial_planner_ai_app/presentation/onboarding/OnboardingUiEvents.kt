package com.example.financial_planner_ai_app.presentation.onboarding

sealed interface OnboardingUiEvents {
    object NavigateToSignUp : OnboardingUiEvents
    object NavigateToLogin : OnboardingUiEvents
}