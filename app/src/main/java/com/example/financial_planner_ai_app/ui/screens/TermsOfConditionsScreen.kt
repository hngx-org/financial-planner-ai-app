package com.example.financial_planner_ai_app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TermsOfConditionsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "TERMS OF CONDITIONS")
        Text(text = "These are our terms and conditions for using our services: ")
    }
}