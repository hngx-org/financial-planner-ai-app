package com.example.financial_planner_ai_app.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financial_planner_ai_app.data.repository.AuthenticationRepo
import com.example.financial_planner_ai_app.data.repository.FormValidationRepo
import com.shegs.hng_auth_library.model.SignupRequest
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
class SignupViewModel @Inject constructor(
    private val authRepo: AuthenticationRepo,
    private val formValidationRepo: FormValidationRepo
) : ViewModel() {


    private val _state = MutableStateFlow(SignUpState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<SignUpUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event: SignUpEvents) {
        when (event) {
            is SignUpEvents.OnConfirmPasswordChanged -> {
                _state.update { it.copy(confirmPassword = event.confirmPassword) }
            }

            is SignUpEvents.OnEmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }

            is SignUpEvents.OnPasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }

            SignUpEvents.OnSignUpClicked -> {
                submitData()
            }

            is SignUpEvents.OnUsernameChanged -> {
                _state.update { it.copy(username = event.name) }
            }

            SignUpEvents.OnLoginClicked -> {
                viewModelScope.launch {
                    _eventFlow.emit(SignUpUiEvents.NavigateToLogin)
                }
            }

            SignUpEvents.OnPasswordVisibilityClicked -> {
                _state.update { it.copy(passwordVisibility = !_state.value.passwordVisibility) }
            }
        }
    }

    private fun submitData() {
        val usernameResult = formValidationRepo.validateUsername(_state.value.username)
        val emailResult = formValidationRepo.validateEmail(_state.value.email)
        val passwordResult = formValidationRepo.validatePassword(_state.value.password)
        val confirmPasswordResult = formValidationRepo.validateConfirmPassword(
            _state.value.password,
            _state.value.confirmPassword
        )

        val hasError = listOf(
            usernameResult,
            emailResult,
            passwordResult,
            confirmPasswordResult
        ).any { !it.successful }

        if (hasError) {
            _state.update {
                it.copy(
                    usernameError = usernameResult.message,
                    emailError = emailResult.message,
                    passwordError = passwordResult.message,
                    confirmPasswordError = confirmPasswordResult.message
                )
            }
            return
        } else {
            signUp(
                SignupRequest(
                    _state.value.username,
                    _state.value.email,
                    _state.value.password,
                    _state.value.confirmPassword
                )
            )
        }
    }

    private fun signUp(signupRequest: SignupRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(loading = true) }
            delay(3000)
            when (val result = authRepo.signup(signupRequest)) {
                is ApiResponse.Error -> {
                    _state.update { it.copy(loading = false) }
                    _eventFlow.emit(SignUpUiEvents.ShowSnackbar(result.message + "An error occurred"))
                }

                is ApiResponse.Success -> {
                    delay(3000)
                    _state.update { it.copy(loading = false) }
                    _eventFlow.emit(SignUpUiEvents.ShowSnackbar("Account created"))
                    delay(1500)
                    _eventFlow.emit(SignUpUiEvents.NavigateToLogin)
                }
            }
        }
    }

}