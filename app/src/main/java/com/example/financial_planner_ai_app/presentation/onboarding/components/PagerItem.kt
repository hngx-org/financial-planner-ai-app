package com.example.financial_planner_ai_app.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financial_planner_ai_app.R

@Composable
fun Page(
    imageId: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth(0.9f)
                .height(350.dp)
                .clip(MaterialTheme.shapes.medium)
                .border(1.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium)
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Text(
            text = description,
            modifier = modifier
                .fillMaxWidth(0.8f),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
    }

}


@Preview
@Composable
fun PagePreview() {
    Page(imageId = R.drawable.finance_ai, description = "Introducing you to your finance buddy!")
}