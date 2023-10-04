package com.example.financial_planner_ai_app.presentation.navigation

sealed class Destinations(val route: String) {

    object SplashScreen : Destinations("splashscreen")
    object OnboardingScreen : Destinations("onboarding_screen")
    object LoginScreen : Destinations("login_screen")
    object SignUpScreen : Destinations("signup_screen")
    object PrivacyPolicy : Destinations("privacy_policy_screen")
    object BottomNavGraph : Destinations("bottom_nav_graph")
}


