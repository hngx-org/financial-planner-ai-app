package com.example.financial_planner_ai_app.data.remote

import com.example.financial_planner_ai_app.data.model.AuthenticationResponse
import com.example.financial_planner_ai_app.data.model.LoginData
import com.example.financial_planner_ai_app.data.model.LogoutResponse
import com.example.financial_planner_ai_app.data.model.SignUpData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OpenApiService {

    @POST("api/auth/register")
    suspend fun registerUser(@Body signUpData: SignUpData) : Response<AuthenticationResponse>

    @POST("api/auth/login")
    suspend fun login(@Body loginData: LoginData) : Response<AuthenticationResponse>

    @GET("api/auth/logout")
    suspend fun logout() : Response<LogoutResponse>
}