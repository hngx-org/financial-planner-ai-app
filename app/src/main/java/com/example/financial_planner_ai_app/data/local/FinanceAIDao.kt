package com.example.financial_planner_ai_app.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financial_planner_ai_app.data.local.model.InteractionRecord

@Dao
interface FinanceAIDao {

    @Query("SELECT * FROM interaction_records")
    fun getAllInteractions(): List<InteractionRecord>

    @Query("SELECT * FROM interaction_records WHERE interactionId = :id")
    fun getInteractionById(id: Int): InteractionRecord

    @Query("SELECT * FROM interaction_records WHERE prompt LIKE :prompt")
    fun findInteractionByPrompt(prompt: String): List<InteractionRecord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInteraction(interactionRecord: InteractionRecord)

    @Delete
    fun deleteInteraction(interactionRecord: InteractionRecord)
}