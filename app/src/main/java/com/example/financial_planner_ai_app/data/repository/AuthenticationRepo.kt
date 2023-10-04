package com.example.financial_planner_ai_app.data.repository

import android.content.Context
import com.shegs.hng_auth_library.authlibrary.AuthLibrary
import com.shegs.hng_auth_library.model.AuthResponse
import com.shegs.hng_auth_library.model.LoginRequest
import com.shegs.hng_auth_library.model.LogoutResponse
import com.shegs.hng_auth_library.model.SignupRequest
import com.shegs.hng_auth_library.network.ApiResponse

class AuthenticationRepo(
    context: Context
) {
    private val apiService = AuthLibrary.createAuthService()
    private val authDataStoreRepository = AuthLibrary.createDataStoreRepository(context)
    private val signupRepo = AuthLibrary.createSignupRepository(apiService)
    private val loginRepo = AuthLibrary.createLoginRepository(apiService, authDataStoreRepository)
    private val logoutRepo = AuthLibrary.createLogoutRepository(apiService)
    private val profileRepo = AuthLibrary.createProfileRepository(apiService)

    suspend fun signup(signupRequest: SignupRequest): ApiResponse<AuthResponse> {
        return signupRepo.signup(signupRequest)
    }

    suspend fun login(loginRequest: LoginRequest): ApiResponse<AuthResponse> {
        return loginRepo.login(loginRequest)
    }

    suspend fun logout() : ApiResponse<LogoutResponse> {
       return logoutRepo.logout()
    }

    suspend fun getUserProfile() : ApiResponse<AuthResponse> {
        return profileRepo.profile()
    }

}