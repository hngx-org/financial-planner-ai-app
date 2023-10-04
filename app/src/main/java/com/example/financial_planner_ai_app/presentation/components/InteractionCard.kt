package com.example.financial_planner_ai_app.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InteractionCard(
    prompt: String,
    response: String,
    icon: ImageVector,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = prompt,
            textAlign = TextAlign.Start,
            letterSpacing = 0.07.sp,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Surface(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.tertiary,
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.tertiaryContainer),
            tonalElevation = 12.dp
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = response,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.07.sp ,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            IconButton(
                onClick = onIconClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    }
}