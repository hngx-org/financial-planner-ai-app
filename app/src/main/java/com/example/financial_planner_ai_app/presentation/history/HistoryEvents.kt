package com.example.financial_planner_ai_app.presentation.history

import com.example.financial_planner_ai_app.data.local.model.InteractionRecord

sealed interface HistoryEvents {

    data class OnRecordClicked(val record: InteractionRecord) : HistoryEvents
    object OnSearchClicked : HistoryEvents
    data class OnDeleteRecordClicked(val record: InteractionRecord) : HistoryEvents
    data class OnQueryChanged(val query: String) : HistoryEvents
}