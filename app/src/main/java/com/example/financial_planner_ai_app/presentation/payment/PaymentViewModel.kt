package com.example.financial_planner_ai_app.presentation.payment

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(): ViewModel() {
    private val _paymentUiState = MutableStateFlow(PaymentScreenUiState())
    val paymentScreenUiState = _paymentUiState

    fun updateUserFeedback(userInput: PaymentScreenUiState) {
        _paymentUiState.value = userInput
    }
}