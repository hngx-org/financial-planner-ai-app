package com.example.financial_planner_ai_app.data.repository

import com.example.financial_planner_ai_app.data.local.FinanceAIDatabase
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val financeAIDatabase: FinanceAIDatabase
){
    val dao = financeAIDatabase.financeAIDao()
}