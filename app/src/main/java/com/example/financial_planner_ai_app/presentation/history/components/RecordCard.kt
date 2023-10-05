package com.example.financial_planner_ai_app.presentation.history.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
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
fun RecordCard(
    prompt: String,
    response: String,
    expand: Boolean,
    icon: ImageVector,
    onExpand: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Surface(
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            tonalElevation = 12.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = prompt,
                    textAlign = TextAlign.Start,
                    letterSpacing = 0.07.sp,
                )
                Spacer(modifier = Modifier.width(6.dp))
                IconButton(onClick = onExpand) {
                    Icon(
                        imageVector = if (expand) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = null
                    )
                }
            }
        }
        AnimatedVisibility(visible = expand) {
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                modifier = modifier,
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.tertiary,
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.tertiaryContainer),
                tonalElevation = 12.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = response,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Cursive,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.07.sp,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            IconButton(
                onClick = onDelete,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    }

}