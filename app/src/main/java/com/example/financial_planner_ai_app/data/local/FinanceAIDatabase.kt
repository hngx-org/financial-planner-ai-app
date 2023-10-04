package com.example.financial_planner_ai_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.financial_planner_ai_app.data.local.model.InteractionRecord

@Database(entities = [InteractionRecord::class], version = 1)
abstract class FinanceAIDatabase : RoomDatabase() {
    abstract fun financeAIDao() : FinanceAIDao
}