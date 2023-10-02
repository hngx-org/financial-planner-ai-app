package com.example.financial_planner_ai_app.presentation.onboarding

sealed interface OnboardingEvents {
    object OnSkipClicked : OnboardingEvents
    object OnBeginClicked : OnboardingEvents
}