package com.example.financial_planner_ai_app.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarDestination(val route: String, val label: String, val icon: ImageVector) {
    object HomeScreen : BottomBarDestination("home_screen", "home", Icons.Filled.Home)
    object Payments : BottomBarDestination("payment_screen", "payments", Icons.Filled.AccountBalanceWallet)
    object History : BottomBarDestination("history_screen", "history", Icons.Filled.Timeline)
}