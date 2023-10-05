import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.financial_planner_ai_app.presentation.homeScreen.HomeEvents
import com.example.financial_planner_ai_app.presentation.homeScreen.HomeUiEvents
import com.example.financial_planner_ai_app.presentation.homeScreen.HomeUiState
import com.example.financial_planner_ai_app.presentation.homeScreen.HomeViewModel
import com.example.financial_planner_ai_app.presentation.homeScreen.components.HomeTopBar
import com.example.financial_planner_ai_app.presentation.homeScreen.components.InteractionCard
import com.example.financial_planner_ai_app.presentation.homeScreen.components.ProfileCard
import com.example.financial_planner_ai_app.presentation.navigation.Destinations
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState().value
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                HomeUiEvents.LogOut -> {
                    navController.navigate(Destinations.LoginScreen.route) {
                        popUpTo(Destinations.BottomNavGraph.route) {
                            inclusive = true
                        }
                    }
                }

                is HomeUiEvents.ShowSnackbar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(event.message)
                    }
                }
            }
        }
    }

    HomeScreenContent(
        state = state,
        snackbarHostState = snackbarHostState,
        onEvent = viewModel::onEvent
    )

}

@Composable
fun HomeScreenContent(
    state: HomeUiState,
    snackbarHostState: SnackbarHostState,
    onEvent: (HomeEvents) -> Unit
) {

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        topBar = {
            HomeTopBar(
                subject = state.userData.name,
                onProfileClick = { onEvent(HomeEvents.OnProfileToggle) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {


            item {
                AnimatedVisibility(visible = state.loading) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        LinearProgressIndicator(color = MaterialTheme.colorScheme.inversePrimary)
                    }
                }
            }

            item {
                AnimatedVisibility(visible = state.showInteractionCard) {
                    InteractionCard(
                        prompt = state.prompt,
                        response = state.aiResponse,
                        icon = Icons.Filled.Save,
                        onSave = { onEvent(HomeEvents.OnSaveInteraction) },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }

            item {
                AnimatedVisibility(visible = state.showUserProfile) {
                    Dialog(onDismissRequest = { onEvent(HomeEvents.OnProfileToggle) }) {
                        ProfileCard(
                            userData = state.userData,
                            onLogOut = { onEvent(HomeEvents.OnLogout) }
                        )
                    }
                }
            }


            item {
                OutlinedTextField(
                    value = state.prompt,
                    onValueChange = { onEvent(HomeEvents.OnQueryChanged(it)) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    trailingIcon = {
                        IconButton(onClick = { onEvent(HomeEvents.OnGenerateChatResponse) }) {
                            Icon(imageVector = Icons.Filled.Send, contentDescription = null)
                        }
                    }
                )
            }
        }
    }

}