package com.example.financial_planner_ai_app.presentation.homeScreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController
) {
    var expanded by remember { mutableStateOf(false) }
    val homeScreenUiState by homeScreenViewModel.homeScreenUiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Hey Eliud,",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    modifier = Modifier.size(44.dp),

                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("History") },
                    onClick = {navController.navigate("history_screen") }
                )
                DropdownMenuItem(
                    text = { Text("Subscription") },
                    onClick = { navController.navigate("payment_screen")}
                )
            }
        }
        Text(
            text = "Welcome back to Finance Planner App",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 20.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(80.dp)
            ) {
                Text(text = "This is a sample prompt")
            }
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(80.dp)
            ) {
                Text(text = "This is a sample prompt")
            }
        }
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(80.dp)
            ) {
                Text(text = "This is a sample prompt")
            }
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(80.dp)
            ) {
                Text(text = "This is a sample prompt")
            }
        }
        Text(
            text = "What would you like our model to help you with regarding financial literature?",
            modifier = Modifier.padding(10.dp),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(80.dp)
            ) {
                Text(text = "Budgeting your income")
            }
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(80.dp)
            ) {
                Text(text = "Increasing your investment portfolio")
            }
        }
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(80.dp)
            ) {
                Text(text = "Gov't Bonds and MMFs")
            }
            Card(
                modifier = Modifier
                    .width(170.dp)
                    .height(80.dp)
            ) {
                Text(text = "Dealing with trading brokers")
            }
        }
        OutlinedTextField(
            value = homeScreenUiState.searchText,
            onValueChange = {
                homeScreenViewModel.updateSearchPrompt(homeScreenUiState.copy(searchText = it))
            },
            label = { Text(text = "Enter your finance question") },
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth(0.9f),
            singleLine = false
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(top = 20.dp)
                .height(60.dp)
                .fillMaxWidth(0.9f)
        ) {
            Text(
                text = "Send your question",
                fontSize = 20.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(
        homeScreenViewModel = HomeScreenViewModel(),
        navController = navController)
}