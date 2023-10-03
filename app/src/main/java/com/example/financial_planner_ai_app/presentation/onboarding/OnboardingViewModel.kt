package com.example.financial_planner_ai_app.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financial_planner_ai_app.data.repository.DataStoreRepository
import com.example.financial_planner_ai_app.presentation.splash.SplashUiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(OnboardingState())
    val state = _state.asStateFlow()

    private val _onboardingFlow = MutableSharedFlow<OnboardingUiEvents>()
    val onboardingFlow = _onboardingFlow.asSharedFlow()

    private val _splashFlow = MutableSharedFlow<SplashUiEvents>()
    val splashFlow = _splashFlow.asSharedFlow()

    init {
        readOnboardingState()
    }

    fun onEvent(event: OnboardingEvents) {
        when (event) {
            OnboardingEvents.OnBeginClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    dataStoreRepository.saveOnboardingState(true)
                    _onboardingFlow.emit(OnboardingUiEvents.NavigateToSignUp)
                }
            }

            OnboardingEvents.OnSkipClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    dataStoreRepository.saveOnboardingState(true)
                    _onboardingFlow.emit(OnboardingUiEvents.NavigateToLogin)
                }
            }
        }
    }

    private fun readOnboardingState() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    isOnboarded = dataStoreRepository.readOnboardingState().stateIn(this).value,
                    isLoggedIn = dataStoreRepository.readLoggedInStatus().stateIn(this).value
                )
            }
            delay(3000)
            if (_state.value.isOnboarded) {
                if (_state.value.isLoggedIn) {
                    _splashFlow.emit(SplashUiEvents.NavigateToHome)
                } else {
                    _splashFlow.emit(SplashUiEvents.NavigateToLogin)
                }
            } else {
                _splashFlow.emit(SplashUiEvents.BeginOnboarding)
            }
        }
    }
}