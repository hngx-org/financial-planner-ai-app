package com.example.financial_planner_ai_app.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.financial_planner_ai_app.R
import com.example.financial_planner_ai_app.presentation.theme.md_theme_light_onPrimary


@Composable
fun Login(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
){
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.atm))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever)
    val loginUiState by loginViewModel.loginState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ){
            LaunchedEffect(key1 = progress){}
            Column {
                LottieAnimation(
                    modifier = Modifier.size(400.dp),
                    composition = composition,
                    progress = {progress},
                   // isPlaying = true
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = stringResource(id = R.string.welcome),
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(10.dp)
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            OutlinedTextField(
                value = loginUiState.emailAddress,
                onValueChange = {},
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(text = stringResource(id = R.string.hint_email))
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                )
            )

            OutlinedTextField(
                value = loginUiState.password,
                onValueChange = {},
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(text = stringResource(id = R.string.hint_password))
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Email
                )
            )

            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    color = md_theme_light_onPrimary,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 18.sp

                )
                
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.2.dp)
            ){
                Text(
                    text = stringResource(id = R.string.dont_have)
                )
                TextButton(
                    onClick = onSignUpClick,
                ) {
                    Text(
                        text = stringResource(id = R.string.click),
                        color = MaterialTheme.colorScheme.primary,
                       fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen(){
    Login(
        onLoginClick = {},
        onSignUpClick = {},
        loginViewModel = LoginViewModel()
    )
}


