package com.example.financial_planner_ai_app.presentation.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financial_planner_ai_app.R
import com.example.financial_planner_ai_app.presentation.onboarding.components.OnBoardingButton
import com.example.financial_planner_ai_app.presentation.onboarding.components.Page
import com.example.financial_planner_ai_app.presentation.theme.FinancialplanneraiappTheme

@Composable
fun OnboardingScreen() {
    // add the OnboardingScreenContent composable here
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreenContent() {

    val pageCount = 3
    val pagerState = rememberPagerState(initialPage = 0)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {

        item {
            AnimatedVisibility(visible = pagerState.currentPage == 0) {
                OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(start = 16.dp)) {
                    Text(text = "Skip", fontWeight = FontWeight.Bold)
                }
            }
        }

        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    pageCount = pageCount,
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    pageSpacing = 16.dp
                ) { page ->
                    when (page) {
                        0 -> {
                            Page(
                                imageId = R.drawable.finance_ai,
                                description = "Introducing you to your finance buddy!"
                            )
                        }

                        1 -> {
                            Page(
                                imageId = R.drawable.financial_analysis,
                                description = "Let's dive into financial planning together."
                            )
                        }

                        2 -> {
                            Page(
                                imageId = R.drawable.financial_chart,
                                description = "Use AI to generate instant insights, future predictions & actionable tips."
                            )
                        }
                    }

                }

                Row(
                    Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(20.dp)

                        )
                    }
                }
            }
        }

        item {
            AnimatedVisibility(visible = pagerState.currentPage == 2) {
                OnBoardingButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    FinancialplanneraiappTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            OnboardingScreenContent()
        }
    }
}