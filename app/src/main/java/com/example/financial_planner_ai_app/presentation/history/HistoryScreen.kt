package com.example.financial_planner_ai_app.presentation.history

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.financial_planner_ai_app.presentation.history.components.RecordCard
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun HistoryScreen(viewModel: HistoryViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is HistoryUiEvents.ShowSnackbar -> {
                    scope.launch { snackbarHostState.showSnackbar(event.message) }
                }
            }
        }
    }

    HistoryScreenContent(
        state = state,
        snackbarHostState = snackbarHostState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun HistoryScreenContent(
    state: HistoryState,
    snackbarHostState: SnackbarHostState,
    onEvent: (HistoryEvents) -> Unit
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = state.query,
                    onValueChange = { onEvent(HistoryEvents.OnQueryChanged(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    label = {
                        Text(text = "Search for saved interactions")
                    },
                    trailingIcon = {
                        IconButton(onClick = { onEvent(HistoryEvents.OnSearchClicked) }) {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                        }
                    }
                )
            }
        }
    ) { contentPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            item {
                AnimatedVisibility(visible = state.loading) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        LinearProgressIndicator(color = MaterialTheme.colorScheme.inversePrimary)
                    }
                }
            }

            items(state.interactions) { record ->
                RecordCard(
                    prompt = record.prompt,
                    response = record.aiResponse,
                    expand = state.expandRecord,
                    icon = Icons.Filled.DeleteForever,
                    onExpand = { onEvent(HistoryEvents.OnExpandRecord) },
                    onDelete = { onEvent(HistoryEvents.OnDeleteRecordClicked(record)) }
                )
            }
        }

    }
}