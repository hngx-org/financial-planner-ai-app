package com.example.financial_planner_ai_app.presentation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.financial_planner_ai_app.presentation.navigation.BottomBarDestination

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomBarDestination.HomeScreen,
        BottomBarDestination.History,
        BottomBarDestination.Payments
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (items.any { it.route == currentDestination?.route }) {
        NavigationBar(modifier = modifier) {

            items.forEach { screen ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(BottomBarDestination.HomeScreen.route) {
                                inclusive = false
                            }

                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = screen.icon ?: Icons.Filled.Favorite,
                            contentDescription = screen.route
                        )
                    },
                    label = {
                        Text(text = screen.label ?: "N/A")
                    }
                )
            }

        }
    }
}
