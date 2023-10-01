package com.example.financial_planner_ai_app.data.repository

import com.example.financial_planner_ai_app.data.model.AuthenticationResponse
import com.example.financial_planner_ai_app.data.model.LoginData
import com.example.financial_planner_ai_app.data.model.LogoutResponse
import com.example.financial_planner_ai_app.data.model.SignUpData
import com.example.financial_planner_ai_app.data.remote.OpenApiService
import com.example.financial_planner_ai_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val apiService: OpenApiService
) {

    fun signUp(signUpData: SignUpData): Flow<Resource<AuthenticationResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.registerUser(signUpData)
                if (response.isSuccessful) {
                    emit(Resource.Success(data = response.body()))
                } else {
                    return@flow
                }

            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An unknown error occurred"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An unknown error occurred"))
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage ?: "An unknown error occurred"))
        }
    }

    fun login(loginData: LoginData): Flow<Resource<AuthenticationResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.login(loginData)
                if (response.isSuccessful) {
                    emit(Resource.Success(data = response.body()))
                } else {
                    return@flow
                }

            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An unknown error occurred"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An unknown error occurred"))
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage ?: "An unknown error occurred"))
        }
    }

    fun getLoggedInUser(username: String): Flow<Resource<AuthenticationResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getLoggedInUser(username)
                if (response.isSuccessful) {
                    emit(Resource.Success(data = response.body()))
                } else {
                    return@flow
                }
            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An unknown error occurred"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An unknown error occurred"))
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage ?: "An unknown error occurred"))
        }
    }

    fun logout(): Flow<Resource<LogoutResponse?>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.logout()
                if (response.isSuccessful) {
                    emit(Resource.Success(data = response.body()))
                } else {
                    return@flow
                }
            } catch (e: IOException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An unknown error occurred"))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.localizedMessage ?: "An unknown error occurred"))
            }
        }.catch { e ->
            emit(Resource.Error(message = e.localizedMessage ?: "An unknown error occurred"))
        }
    }

}