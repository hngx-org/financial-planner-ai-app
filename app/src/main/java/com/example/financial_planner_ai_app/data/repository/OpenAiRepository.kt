package com.example.financial_planner_ai_app.data.repository

import com.example.apilibrary.wrapperclass.OpenAiCaller
import com.example.financial_planner_ai_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class OpenAiRepository {

    fun generateChatResponse(userPrompt: String, userId: String): Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = OpenAiCaller.generateChatResponse(userPrompt, userId)
                emit(Resource.Success(response))
            } catch (e: IOException) {
                emit(Resource.Error("Check your connection status."))
            } catch (e: HttpException) {
                emit(Resource.Error("A server error occurred."))
            }
        }
    }

}