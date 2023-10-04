package com.example.financial_planner_ai_app.presentation.navigation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.financial_planner_ai_app.presentation.history.HistoryScreen
import com.example.financial_planner_ai_app.presentation.onboarding.OnboardingScreen
import com.example.financial_planner_ai_app.presentation.payment.PaymentScreen
import com.example.financial_planner_ai_app.presentation.privacypolicy.PrivacyPolicyScreen
import com.example.financial_planner_ai_app.presentation.signup.SignupScreen
import com.example.financial_planner_ai_app.presentation.splash.SplashScreen
import com.example.financial_planner_ai_app.ui.login_screen.LoginScreen


@Composable
fun FinanceAIApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        modifier = modifier,
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

        composable(route = Destinations.SignUpScreen.route) {
            SignupScreen(navController = navController)
        }
        bottomBarGraph(navController = navController)

        composable(route = Destinations.PrivacyPolicy.route) {
            PrivacyPolicyScreen(navController)
        }


    }
}

fun NavGraphBuilder.bottomBarGraph(navController: NavController) {
    navigation(
        startDestination = BottomBarDestination.HomeScreen.route,
        route = Destinations.BottomNavGraph.route
    ) {
        composable(route = BottomBarDestination.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = BottomBarDestination.Payments.route) {
            PaymentScreen(navController = navController)
        }
        composable(route = BottomBarDestination.History.route) {
            HistoryScreen()
        }
    }
}