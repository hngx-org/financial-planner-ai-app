package com.example.financial_planner_ai_app.presentation.signup



import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.financial_planner_ai_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    signupViewModel: SignupViewModel,
    navController: NavController
) {
    var termsAccepted by remember { mutableStateOf(false) }
    val signupUiState by signupViewModel.signupUiState.collectAsState()
    var name by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var text by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    fun validate(text: String) {
        isError = text.count() < 3
    }

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
            value = signupUiState.name,
            onValueChange = { signupViewModel.updateUserInput(signupUiState.copy(name = it)) },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 20.dp),
            label = { Text(text = "Enter name")},
            singleLine = true
        )
        OutlinedTextField(
            value = signupUiState.email,
            onValueChange = {
                signupViewModel.updateUserInput(signupUiState.copy(email = it))
                isError = false
                            },
            label = { Text(if (isError) "Email*" else "Email")},
            isError = isError,
            keyboardActions = KeyboardActions { validate(text) } ,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 20.dp)
                .semantics {
                    if (isError) error("Email format is invalid")
                }
        )
        OutlinedTextField(
            value = signupUiState.password,
            onValueChange = { signupViewModel.updateUserInput(signupUiState.copy(password = it)) },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 20.dp),
            label = { Text(text = "Enter password")},
            singleLine = true,
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None ,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            trailingIcon = {
//                IconButton(onClick = { passwordHidden = !passwordHidden }) {
//                    val visibilityIcon = if (passwordHidden)
//                    val description = if (passwordHidden) "Show password" else "Hide password"
//                    Icon(imageVector = visibilityIcon, contentDescription = description)
//                }
//            }
        )
        OutlinedTextField(
            value = signupUiState.confirmPassword,
            onValueChange = { signupViewModel.updateUserInput(signupUiState.copy(confirmPassword = it)) },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 21.dp),
            label = { Text(text = "Confirm password")},
            singleLine = true,
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None ,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            trailingIcon = {
//                IconButton(onClick = { passwordHidden = !passwordHidden }) {
//                    val visibilityIcon = if (passwordHidden)
//                    val description = if (passwordHidden) "Show password" else "Hide password"
//                    Icon(imageVector = visibilityIcon, contentDescription = description)
//                }
//            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = termsAccepted,
                onCheckedChange = {
                    termsAccepted = it
                    if (!it) {
                        // Checkbox is unchecked, show the popup
                        showDialog = true
                    }
                }
            )
            Text(
                buildAnnotatedString {

                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp
                        )
                    ) {
                        append("I have read and agree to")
                    }
                    withStyle(style = SpanStyle(color = Color.Blue, fontSize = 16.sp)) {
                        append("Terms of conditions\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp
                        )
                    ) {
                        append("and ")
                    }
                    withStyle(style = SpanStyle(color = Color.Blue, fontSize = 16.sp)) {
                        append("Privacy Policy")
                    }
                },
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(bottom = 20.dp)
                    .padding(8.dp)
                    .clickable { navController.navigate("terms&conditions") },
                color = MaterialTheme.colorScheme.primary
            )
        }
        Button(
            onClick = {
                if (termsAccepted) {
                    //navigate
                    navController.navigate("HomeScreen")
                }
            },
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
            onClick = {
                      if (termsAccepted){
                          //navigate
                          navController.navigate("terms&conditions")

                      }
                      },
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
                    contentDescription = "google"
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
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp
                    )
                ) {
                    append("Already have an account?")
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp
                    )
                ) {
                    append(" Login")
                }
            },
            modifier = Modifier.padding(20.dp)
        )
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Terms and Conditions")
            },
            text = {
                Text(text = "Please accept the Terms and Conditions to proceed.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        // Close the dialog and handle as needed
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }}
        )
    }
}


@Composable
fun SignupScreenErrorScreen() {}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {
    val navController = rememberNavController()
    SignupScreen(
        signupViewModel = SignupViewModel(),
        navController = navController
    )
}