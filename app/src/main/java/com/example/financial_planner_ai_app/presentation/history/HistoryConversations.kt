package com.example.financial_planner_ai_app.presentation.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
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
fun HistoryScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "These are your previous conversations: ",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold
        )
        LazyColumn {
            items(10) {
                ConversationCard()
            }
        }
    }
}

@Composable
fun ConversationCard(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth(0.8f)
            .height(150.dp)
    ) {
        Text(
            text = "Chat heading",
            fontSize = 19.sp,
            modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Text body",
            modifier = Modifier.padding(3.dp),
            fontSize = 17.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen()
}
