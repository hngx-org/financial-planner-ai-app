package com.example.financial_planner_ai_app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financial_planner_ai_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    signupUiState: SignupUiState,
    onFirstNameInput: () -> Unit
) {
    var termsAccepted by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "WELCOME",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Sign up to get your finances in order",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 20.dp),
            color = MaterialTheme.colorScheme.primary
        )
        OutlinedTextField(
            value = signupUiState.firstName,
            onValueChange = { onFirstNameInput() },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 20.dp),
            placeholder = { Text(
                text = "Enter first name",
                color = MaterialTheme.colorScheme.primary
            ) }
        )
        OutlinedTextField(
            value = signupUiState.lastName,
            onValueChange = { onFirstNameInput() },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 20.dp),
            placeholder = { Text(
                text = "Enter last name",
                color = MaterialTheme.colorScheme.primary
            ) }
        )
        OutlinedTextField(
            value = signupUiState.email,
            onValueChange = { onFirstNameInput() },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 20.dp),
            placeholder = { Text(
                text = "Enter email",
                color = MaterialTheme.colorScheme.primary
            ) }
        )
        OutlinedTextField(
            value = signupUiState.password,
            onValueChange = { onFirstNameInput() },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 20.dp),
            placeholder = { Text(
                text = "Enter password",
                color = MaterialTheme.colorScheme.primary
            ) }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(
                checked = termsAccepted,
                onCheckedChange = {
                    termsAccepted = it
                }
            )
            Text(
                buildAnnotatedString {

                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 16.sp)){
                                         append("I have read and agree to")
                                     }
                    withStyle(style = SpanStyle(color = Color.Blue, fontSize = 16.sp)){
                                         append("Terms of conditions\n")
                                     }
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 16.sp)){
                                         append("and ")
                                     }
                    withStyle(style = SpanStyle(color = Color.Blue, fontSize = 16.sp)){
                                         append("Privacy Policy")
                                     }
                },
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(bottom = 20.dp)
                    .padding(8.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(20.dp)
                .height(60.dp)
                .fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Sign Up",
                fontSize = 20.sp
            )
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .border(1.dp, Color(233, 221, 231))
                .padding(10.dp)
                .height(60.dp)
                .fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(255, 255, 255)
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription ="google"
                )
                Text(
                    text = "Sign Up with Google",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }

        }
        Text(
            buildAnnotatedString{
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 18.sp)){
                    append("Already have an account?")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 20.sp)) {
                    append(" Login")
                }
            },
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    SignupScreen(
        signupUiState = SignupUiState(
            firstName = "",
            lastName = "",
            email = "",
            password = ""
        ),
        onFirstNameInput = {}
    )
}