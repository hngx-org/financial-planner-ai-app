package com.example.financial_planner_ai_app.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor():ViewModel(){
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState

    fun updateUserEmail(userEmail:LoginState){
        _loginState.value = userEmail
    }

    fun updateUserPassword(userPassword:LoginState){
        _loginState.value = userPassword
    }
}