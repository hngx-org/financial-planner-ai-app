package com.example.financial_planner_ai_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.financial_planner_ai_app.presentation.history.HistoryScreen
import com.example.financial_planner_ai_app.presentation.homeScreen.HomeScreen
import com.example.financial_planner_ai_app.presentation.homeScreen.HomeScreenViewModel
import com.example.financial_planner_ai_app.presentation.login.Login
import com.example.financial_planner_ai_app.presentation.login.LoginViewModel
import com.example.financial_planner_ai_app.presentation.onboarding.OnboardingScreenContent
import com.example.financial_planner_ai_app.presentation.payment.PaymentScreen
import com.example.financial_planner_ai_app.presentation.payment.PaymentViewModel
import com.example.financial_planner_ai_app.presentation.privacypolicy.PrivacyPolicyScreen
import com.example.financial_planner_ai_app.presentation.signup.SignupScreen
import com.example.financial_planner_ai_app.presentation.signup.SignupViewModel
import com.example.financial_planner_ai_app.presentation.splash.SplashScreen
import com.example.financial_planner_ai_app.presentation.termsandconditions.TermsOfConditionsScreen


@Composable
fun FinanceNavHost(
    navHostController: NavHostController
){
    val navController = rememberNavController()
    val signupViewModel = SignupViewModel()
    val homeScreenViewModel = HomeScreenViewModel()
    val paymentViewModel = PaymentViewModel()
    val loginViewModel = LoginViewModel()
    NavHost(
        navController = navController,
        startDestination = "splashscreen"
    ){
        composable(route = "splashscreen"){
            SplashScreen(navController = navController)
        }

        composable(route = "onBoardingScreenContent"){
            OnboardingScreenContent(navController)
        }

        composable(route = "login"){
            Login(
                onLoginClick = { navController.navigate("HomeScreen")
                },

                onSignUpClick = {navController.navigate("signup")},

                loginViewModel = loginViewModel
            )
        }

        composable(route = "privacy_policy"){
            PrivacyPolicyScreen(navController)
        }

        composable(route = "signup"){
            SignupScreen(
                signupViewModel = signupViewModel,
                navController = navController)
        }

        composable(route = "terms&conditions"){
            TermsOfConditionsScreen(
                onTermClick = {navController.navigate("signup")},
                navController = navController
            )
        }

        composable(route = "HomeScreen"){
            HomeScreen(
                homeScreenViewModel = homeScreenViewModel,
                navController = navController)
        }

        composable(route = "payment_screen"){
            PaymentScreen(
                paymentViewModel = paymentViewModel,
                navController = navController )
        }
        composable(route = "history_screen"){
            HistoryScreen()
        }
    }
}