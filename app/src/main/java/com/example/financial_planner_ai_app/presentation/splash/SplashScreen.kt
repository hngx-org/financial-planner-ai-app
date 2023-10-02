package com.example.financial_planner_ai_app.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


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
import com.example.financial_planner_ai_app.presentation.onboarding.OnboardingViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SplashScreen(
    navController:NavController
){
    val scale = remember{
       Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate("onBoardingScreenContent")


    viewModel: OnboardingViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.splashFlow.collectLatest { event ->
            when (event) {
                SplashUiEvents.BeginOnboarding -> {
                    navController.navigate("onboarding_screen") {
                        popUpTo("splashscreen") {
                            inclusive = true
                        }
                    }
                }

                SplashUiEvents.SkipOnboarding -> {
                    navController.navigate("HomeScreen") {
                        popUpTo("splashscreen") {
                            inclusive = true
                        }
                    }
                }
            }
        }

    }

    Box(
        modifier = Modifier.fillMaxSize()

        ){
        Column {
            Image(
                painter = painterResource(id = R.drawable.finance_ai),
                contentDescription = "Logo",
                modifier = Modifier.scale(scale.value)
                    .background(MaterialTheme.colorScheme.background,shape = RoundedCornerShape(10.dp)),

            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.entry_note),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(10.dp)
            )
        }

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