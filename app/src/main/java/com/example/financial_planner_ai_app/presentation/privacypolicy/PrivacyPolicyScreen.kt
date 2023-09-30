package com.example.financial_planner_ai_app.presentation.privacypolicy

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
fun PrivacyPolicyScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "DATA & PRIVACY POLICY",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Financial planner ai app is committed to providing it's users with high quality guides on matters finance. We know that you trust us with the most important aspect to life and thus we ensure no data collection takes place. Our models are pre-trained and thus don't require to collect any info whatsoever. We only ask for your personal details to verify and authenticate you as a user. We appreciate your working with us.",
            modifier = Modifier.padding(20.dp)
        )
        Text(
            text = "We work directly with banks and financial institutions thus when collecting payments there is no risk for any breaches to your accounts. We also have a developer team working round the clock to ensure our services are always up.",
            modifier = Modifier
                .padding(end = 20.dp)
                .padding(start = 20.dp)
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(20.dp)
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
fun PrivacyPolicyScreenPreview() {
    PrivacyPolicyScreen()
}