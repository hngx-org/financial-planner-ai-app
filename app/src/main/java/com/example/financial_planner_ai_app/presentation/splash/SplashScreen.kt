package com.example.financial_planner_ai_app.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financial_planner_ai_app.R
import com.example.financial_planner_ai_app.presentation.navigation.Destinations
import com.example.financial_planner_ai_app.presentation.onboarding.OnboardingViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.splashFlow.collectLatest { event ->
            when (event) {
                SplashUiEvents.BeginOnboarding -> {
                    navController.navigate(Destinations.OnboardingScreen.route) {
                        popUpTo(Destinations.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                }

                SplashUiEvents.SkipOnboarding -> {
                    navController.navigate(Destinations.HomeScreen.route) {
                        popUpTo(Destinations.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.globe_svg),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.lighting(
                MaterialTheme.colorScheme.inversePrimary,
                MaterialTheme.colorScheme.inversePrimary
            )
        )

        Text(
            text = "Finance Planner AI",
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.5f),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            fontFamily = FontFamily.Cursive,
            color = MaterialTheme.colorScheme.primary
        )
    }
}