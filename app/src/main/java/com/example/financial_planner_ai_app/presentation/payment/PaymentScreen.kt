package com.example.financial_planner_ai_app.presentation.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    paymentViewModel: PaymentViewModel
) {
    val paymentScreenUiState by paymentViewModel.paymentScreenUiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "Hello Eliud, ",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            text = "Thank you for considering subscribing to our services. We have two subscription packages, which you are free to opt out any time. The first one is $3 package that gives you 100 prompts per month. The second package is the $5 that comes with unlimited number of prompts per month. You can upgrade and also downgrade at any moment.",
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Card(
                modifier = Modifier
                    .height(80.dp)
                    .width(150.dp)
            ) {
                Text(
                    text = "Subscribe to $3",
                    modifier = Modifier.padding(15.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
            Card(
                modifier = Modifier
                    .height(80.dp)
                    .width(150.dp)
            ) {
                Text(
                    text = "Subscribe to $5",
                    modifier = Modifier.padding(15.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        }
        Text(
            text = "Not satisfied with our service?",
            fontSize = 20.sp
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(15.dp)
                .height(50.dp)
                .fillMaxWidth(0.7f)
        ) {
            Text(
                text = "Unsubscribe",
                fontSize = 20.sp
            )
        }
        Text(
            text = "We hate to see you leave, we'd love to hear your feed back on what can be improved, thank you.",
            fontSize = 18.sp,
            modifier = Modifier.padding(5.dp)
        )
        OutlinedTextField(
            value = paymentScreenUiState.feedbackText,
            onValueChange = {
                paymentViewModel.updateUserFeedback(paymentScreenUiState.copy(feedbackText = it))
            },
            modifier = Modifier.padding(top = 10.dp)
                .padding(bottom = 20.dp)
                .fillMaxWidth(0.9f),
            singleLine = false,
            label = { Text(text = "Enter your feedback") }
        )
        OutlinedTextField(
            value = paymentScreenUiState.email,
            onValueChange = {
                paymentViewModel.updateUserFeedback(paymentScreenUiState.copy(email = it))
            },
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(0.9f),
            singleLine = false,
            label = { Text(text = "Enter your email") }
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(0.7f)
                .height(50.dp)
        ) {
            Text(
                text = "Submit Feedback",
                fontSize = 20.sp
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen(
        paymentViewModel = PaymentViewModel()
    )
}