package com.example.financial_planner_ai_app.presentation.payment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class PaymentViewModel: ViewModel() {
    private val _paymentUiState = MutableStateFlow(PaymentScreenUiState())
    val paymentScreenUiState = _paymentUiState

    fun updateUserFeedback(userInput: PaymentScreenUiState) {
        _paymentUiState.value = userInput
    }
}