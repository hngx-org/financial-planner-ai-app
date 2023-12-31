package com.example.financial_planner_ai_app.presentation.splash

sealed interface SplashUiEvents {
    object BeginOnboarding : SplashUiEvents
    object NavigateToHome : SplashUiEvents
    object NavigateToLogin : SplashUiEvents
}