package com.example.financial_planner_ai_app.presentation.history

import com.example.financial_planner_ai_app.data.local.model.InteractionRecord
import com.example.financial_planner_ai_app.presentation.homeScreen.HomeEvents

sealed interface HistoryEvents {

    data class OnRecordClicked(val record: InteractionRecord) : HistoryEvents
    object OnSearchClicked : HistoryEvents
    data class OnDeleteRecordClicked(val record: InteractionRecord) : HistoryEvents
    object OnExpandRecord : HistoryEvents
    data class OnQueryChanged(val query: String) : HistoryEvents
}