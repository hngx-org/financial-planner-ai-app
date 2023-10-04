package com.example.financial_planner_ai_app.ui.login_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.financial_planner_ai_app.R
import com.example.financial_planner_ai_app.presentation.components.AppLogo
import com.example.financial_planner_ai_app.presentation.components.FormButton
import com.example.financial_planner_ai_app.presentation.components.FormTextField
import com.example.financial_planner_ai_app.presentation.login.LoginEvents
import com.example.financial_planner_ai_app.presentation.login.LoginScreenViewModel
import com.example.financial_planner_ai_app.presentation.login.LoginUiEvents
import com.example.financial_planner_ai_app.presentation.navigation.Destinations
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is LoginUiEvents.ShowSnackBar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(event.message)
                    }
                }

                LoginUiEvents.NavigateToHome -> {
                    navController.navigate(Destinations.BottomNavGraph.route) {
                        popUpTo(Destinations.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }

                LoginUiEvents.NavigateToSignup -> {
                    navController.navigate(Destinations.SignUpScreen.route) {
                        popUpTo(Destinations.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                AppLogo()
            }
        }
    ) { innerPadding ->
        LoginScreenContent(
            state = state,
            onEvent = viewModel::onEvent,
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
        )
    }
}

@Composable
fun LoginScreenContent(
    state: LoginState,
    onEvent: (LoginEvents) -> Unit,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.atm)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

        item {
            AnimatedVisibility(visible = state.loading) {
                LinearProgressIndicator(color = MaterialTheme.colorScheme.inversePrimary)
            }
        }

        item {
            LottieAnimation(
                composition = composition,
                progress = {
                    progress
                },
                modifier = Modifier
                    .size(300.dp)
            )
        }

        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column {
                    Text(
                        text = "Hello there! Welcome.",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Login to your account",
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic
                    )
                }
                Column {
                    FormTextField(
                        label = "Email",
                        value = state.email,
                        onValueChange = { onEvent(LoginEvents.OnEmailChanged(it)) },
                        leadingIcon = Icons.Filled.Email,
                        error = state.emailError != null,
                        modifier = Modifier.fillMaxWidth()
                    )
                    state.emailError?.let {
                        AnimatedVisibility(visible = true) {
                            Text(text = it, color = MaterialTheme.colorScheme.error)
                        }
                    }
                }
                Column {

                    OutlinedTextField(
                        value = state.password,
                        onValueChange = { onEvent(LoginEvents.OnPasswordChanged(it)) },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Enter password") },
                        supportingText = { Text(text = "Enter password", fontSize = 12.sp) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = null
                            )
                        },
                        trailingIcon = {
                            val icon =
                                if (state.passwordVisibility) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
                            IconButton(onClick = { onEvent(LoginEvents.OnPasswordVisibilityClicked) }) {
                                Icon(imageVector = icon, contentDescription = null)
                            }
                        },
                        isError = state.passwordError != null,
                        visualTransformation = if (state.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus(true)
                        }),
                        singleLine = true,
                        shape = MaterialTheme.shapes.medium
                    )
                    state.passwordError?.let {
                        AnimatedVisibility(visible = true) {
                            Text(text = it, color = MaterialTheme.colorScheme.error)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                FormButton(
                    label = "Login",
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { onEvent(LoginEvents.OnLoginClicked) }
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(modifier = Modifier.padding(bottom = 12.dp)) {
                    Text(
                        text = "Don't have an Account?",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.07.sp,
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "Sign Up",
                        modifier = Modifier
                            .clickable { onEvent(LoginEvents.OnSignupClicked) },
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.07.sp,
                    )
                }
            }
        }

    }
}


@Preview
@Composable
fun PreviewLoginScreen() {
    Surface(
        Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LoginScreenContent(state = LoginState(), onEvent = {})
    }
}


