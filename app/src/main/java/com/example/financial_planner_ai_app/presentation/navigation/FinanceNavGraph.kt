package com.example.financial_planner_ai_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.financial_planner_ai_app.presentation.history.HistoryScreen
import com.example.financial_planner_ai_app.presentation.homeScreen.HomeScreen
import com.example.financial_planner_ai_app.presentation.onboarding.OnboardingScreen
import com.example.financial_planner_ai_app.presentation.payment.PaymentScreen
import com.example.financial_planner_ai_app.presentation.privacypolicy.PrivacyPolicyScreen
import com.example.financial_planner_ai_app.presentation.signup.SignupScreen
import com.example.financial_planner_ai_app.presentation.splash.SplashScreen
import com.example.financial_planner_ai_app.ui.login_screen.LoginScreen


@Composable
fun FinanceAIAppNavHost(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Destinations.SplashScreen.route
    ) {
        composable(route = Destinations.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Destinations.OnboardingScreen.route) {
            OnboardingScreen(navController = navController)
        }

        composable(route = Destinations.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Destinations.PrivacyPolicy.route) {
            PrivacyPolicyScreen(navController)
        }

        composable(route = Destinations.SignUpScreen.route) {
            SignupScreen(navController = navController)
        }

//        composable(route = "terms&conditions") {
//            TermsOfConditionsScreen(
//                onTermClick = { navController.navigate("signup") },
//                navController = navController
//            )
//        }

        composable(route = Destinations.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Destinations.Payments.route) {
            PaymentScreen(navController = navController)
        }
        composable(route = Destinations.History.route) {
            HistoryScreen()
        }
    }
}