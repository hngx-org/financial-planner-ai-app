package com.example.financial_planner_ai_app.presentation.termsandconditions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TermsOfConditionsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "TERMS & CONDITIONS",
            modifier = Modifier.padding(20.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "These are our terms and conditions for using our services: ",
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "1. You agree to only prompt the AI using moral based question. For example you cannot prompt the model on matters scamming.",
            modifier = Modifier.padding(5.dp)
            )
        Text(
            text = "2. You agree to only create a single account. Using correct credentials which correspond to your bank details",
            modifier = Modifier.padding(5.dp)
            )
        Text(
            text = "3. You agree to be the only user using the account. We do not allow for account sharing.",
            modifier = Modifier.padding(5.dp)
            )
        Text(
            text = "4. You agree to give your own payment details for premium subscriptions. We are against using stolen bank details.",
            modifier = Modifier.padding(5.dp)
            )
        Text(
            text = "5. You agree to give the app correct info so as to ensure the replies as focused to your situation.",
            modifier = Modifier.padding(5.dp)
            )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(20.dp)
                .height(60.dp)
                .fillMaxWidth(0.8f),

            ) {
            Text(
                text = "Back To Signup",
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TermsOfConditionsScreenPreview() {
    TermsOfConditionsScreen()
}