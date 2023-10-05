package com.example.financial_planner_ai_app.presentation.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financial_planner_ai_app.data.local.model.InteractionRecord
import com.example.financial_planner_ai_app.data.repository.AuthenticationRepo
import com.example.financial_planner_ai_app.data.repository.DataStoreRepository
import com.example.financial_planner_ai_app.data.repository.DatabaseRepository
import com.example.financial_planner_ai_app.data.repository.OpenAiRepository
import com.example.financial_planner_ai_app.util.Resource
import com.shegs.hng_auth_library.network.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authenticationRepo: AuthenticationRepo,
    private val dataStoreRepository: DataStoreRepository,
    private val openAiRepository: OpenAiRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<HomeUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getUserId()
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
                _state.update { it.copy(prompt = event.query) }
            }

            HomeEvents.OnGenerateChatResponse -> {
                generateChatResponse(_state.value.prompt)
            }

            HomeEvents.OnSaveInteraction -> {
                saveInteraction()
            }
        }
    }

    private fun saveInteraction() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.addInteraction(
                InteractionRecord(
                    prompt = _state.value.prompt,
                    aiResponse = _state.value.aiResponse
                )
            )
            _state.update { it.copy(showInteractionCard = false) }
        }
    }

    private fun generateChatResponse(userPrompt: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(showInteractionCard = false) }
            openAiRepository.generateChatResponse(
                userPrompt,
                _state.value.userId
            ).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update { it.copy(loading = false) }
                        _eventFlow.emit(HomeUiEvents.ShowSnackbar("LMAO..trust your gut on this." + result.errorMessage))
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                showInteractionCard = true,
                                aiResponse = result.data ?: "Could not generate data"
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    private fun getUserId() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(userId = authenticationRepo.getUserId().stateIn(this).value) }
        }
    }

    private fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(loading = true) }
            val response = authenticationRepo.getUserProfile()
            when (response) {
                is ApiResponse.Error -> {
                    _state.update { it.copy(loading = false) }
                    _eventFlow.emit(HomeUiEvents.ShowSnackbar("Failed to get user data...smh" + response.message))
                }

                is ApiResponse.Success -> {
                    _state.update { it.copy(loading = false) }
                    _state.update { it.copy(userData = response.data.data) }
                }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(loading = true) }
            val response = authenticationRepo.getUserProfile()
            when (response) {
                is ApiResponse.Error -> {
                    _state.update { it.copy(loading = false) }
                    _eventFlow.emit(HomeUiEvents.ShowSnackbar("An error occurred while logging out." + response.message))
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