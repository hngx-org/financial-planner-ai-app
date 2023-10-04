package com.example.financial_planner_ai_app.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interaction_records")
data class InteractionRecord(
    @PrimaryKey(autoGenerate = true)
    val interactionId: Int? = null,
    val prompt: String,
    val aiResponse: String
)
