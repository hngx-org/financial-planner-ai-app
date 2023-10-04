package com.example.financial_planner_ai_app.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financial_planner_ai_app.data.repository.AuthenticationRepo
import com.example.financial_planner_ai_app.data.repository.DataStoreRepository
import com.example.financial_planner_ai_app.data.repository.FormValidationRepo
import com.example.financial_planner_ai_app.ui.login_screen.LoginState
import com.shegs.hng_auth_library.model.LoginRequest
import com.shegs.hng_auth_library.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authenticationRepo: AuthenticationRepo,
    private val formValidationRepo: FormValidationRepo,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<LoginUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.OnEmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }

            is LoginEvents.OnPasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }

            LoginEvents.OnLoginClicked -> {
                submitData()
            }

            LoginEvents.OnSignupClicked -> {
                viewModelScope.launch {
                    _eventFlow.emit(LoginUiEvents.NavigateToSignup)
                }
            }

            LoginEvents.OnPasswordVisibilityClicked -> {
                _state.update { it.copy(passwordVisibility = !_state.value.passwordVisibility) }
            }
        }
    }

    private fun submitData() {
        val emailResult = formValidationRepo.validateEmail(_state.value.email)
        val passwordResult = formValidationRepo.validatePassword(_state.value.password)

        val hasError = listOf(
            emailResult,
            passwordResult,
        ).any { !it.successful }

        if (hasError) {
            _state.update {
                it.copy(
                    emailError = emailResult.message,
                    passwordError = passwordResult.message,
                )
            }
            return
        } else {
            login(
                LoginRequest(
                    _state.value.email,
                    _state.value.password
                )
            )
        }
    }

    private fun login(loginRequest: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(loading = true) }
            delay(3000)
            val response = authenticationRepo.login(loginRequest)
            when (response) {
                is ApiResponse.Error -> {
                    _state.update { it.copy(loading = false) }
                    _eventFlow.emit(LoginUiEvents.ShowSnackBar(response.message))
                }

                is ApiResponse.Success -> {
                    _state.update { it.copy(loading = false) }
                    dataStoreRepository.saveLoggedInStatus(true)
                    authenticationRepo.saveUserId(response.data.data.id)
                    _eventFlow.emit(LoginUiEvents.ShowSnackBar("Success"))
                    delay(1500)
                    _eventFlow.emit(LoginUiEvents.NavigateToHome)
                }
            }
        }
    }


}