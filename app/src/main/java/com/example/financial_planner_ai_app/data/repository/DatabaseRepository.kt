package com.example.financial_planner_ai_app.data.repository

import com.example.financial_planner_ai_app.data.local.FinanceAIDatabase
import com.example.financial_planner_ai_app.data.local.model.InteractionRecord
import com.example.financial_planner_ai_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    financeAIDatabase: FinanceAIDatabase
) {
    val dao = financeAIDatabase.financeAIDao()

    fun getAllInteractions(): Flow<Resource<List<InteractionRecord>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = dao.getAllInteractions()
                emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage ?: "Failed to load interactions."))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An exception occurred."))
            }
        }
    }

    fun findInteractionsByPrompt(prompt: String): Flow<Resource<List<InteractionRecord>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = dao.findInteractionByPrompt(prompt)
                emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage ?: "Failed to load interactions."))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An exception occurred."))
            }
        }
    }

    suspend fun addInteraction(interactionRecord: InteractionRecord) {
        dao.insertInteraction(interactionRecord)
    }

    suspend fun deleteInteractionRecord(interactionRecord: InteractionRecord) {
        dao.deleteInteraction(interactionRecord)
    }
}