package com.example.financial_planner_ai_app.util

sealed class Resource<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(message: String) : Resource<T>(errorMessage = message)
}