package com.example.financial_planner_ai_app.presentation.history

import com.example.financial_planner_ai_app.data.local.model.InteractionRecord

data class HistoryState(
    val interactions: List<InteractionRecord> = emptyList(),
    val loading: Boolean = false,
    val query: String = "",
    val showRecord: Boolean = false,
    val selectedRecord: InteractionRecord? = null
)
