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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecordCard(
    title: String,
    content: String,
    icon: ImageVector,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {

    var expand by remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier) {
        Column {
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
                        text = title,
                        textAlign = TextAlign.Start,
                        letterSpacing = 0.07.sp,
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    IconButton(onClick = { expand = !expand }) {
                        Icon(
                            imageVector = if (expand) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                            contentDescription = null
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            AnimatedVisibility(visible = expand) {
                Surface(
                    modifier = modifier,
                    shape = MaterialTheme.shapes.small,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                    tonalElevation = 12.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = content,
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Cursive,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.07.sp
                        )
                    }
                }
            }
        }
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            IconButton(
                onClick = onDelete,
                modifier = Modifier.padding(4.dp)
            ) {
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    }

}