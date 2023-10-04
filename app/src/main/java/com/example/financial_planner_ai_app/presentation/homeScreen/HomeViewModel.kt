package com.example.financial_planner_ai_app.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financial_planner_ai_app.data.repository.AuthenticationRepo
import com.example.financial_planner_ai_app.data.repository.DataStoreRepository
import com.shegs.hng_auth_library.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authenticationRepo: AuthenticationRepo,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<HomeUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getUserData()
    }

    fun onEvent(event: HomeEvents) {
        when (event) {
            HomeEvents.OnProfileToggle -> {
                _state.update { it.copy(showUserProfile = !_state.value.showUserProfile) }
            }

            HomeEvents.OnLogout -> {
                logout()
            }

            is HomeEvents.OnQueryChanged -> {
                _state.update { it.copy(query = event.query) }
            }
        }
    }

    private fun getUserData() {
        viewModelScope.launch {
            when (authenticationRepo.getUserProfile()) {
                is ApiResponse.Error -> {
                    _eventFlow.emit(HomeUiEvents.ShowSnackbar("Failed to get user data...try again"))
                }

                is ApiResponse.Success -> {
                    _state.update { it.copy(userData = it.userData) }
                }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(loading = true) }
            when (authenticationRepo.logout()) {
                is ApiResponse.Error -> {
                    _state.update { it.copy(loading = false) }
                    _eventFlow.emit(HomeUiEvents.ShowSnackbar("An error occurred while logging out."))
                }

                is ApiResponse.Success -> {
                    _state.update { it.copy(loading = false) }
                    dataStoreRepository.saveLoggedInStatus(false)
                    _eventFlow.emit(HomeUiEvents.LogOut)
                }
            }
        }
    }
}