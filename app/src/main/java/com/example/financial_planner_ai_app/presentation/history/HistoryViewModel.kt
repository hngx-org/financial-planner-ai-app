package com.example.financial_planner_ai_app.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financial_planner_ai_app.data.local.model.InteractionRecord
import com.example.financial_planner_ai_app.data.repository.DatabaseRepository
import com.example.financial_planner_ai_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<HistoryUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        loadAllRecords()
    }

    fun onEvent(event: HistoryEvents) {
        when (event) {
            is HistoryEvents.OnDeleteRecordClicked -> {
                deleteRecord(event.record)
                _state.update { it.copy(interactions = _state.value.interactions) }
            }

            is HistoryEvents.OnQueryChanged -> {
                _state.update { it.copy(query = event.query) }
            }

            is HistoryEvents.OnRecordClicked -> {
                _state.update {
                    it.copy(
                        showRecord = !_state.value.showRecord,
                        selectedRecord = event.record
                    )
                }
            }

            HistoryEvents.OnSearchClicked -> {
                findRecordByPrompt(_state.value.query)
            }
        }
    }

    private fun loadAllRecords() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.getAllInteractions().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update { it.copy(loading = false) }
                        _eventFlow.emit(
                            HistoryUiEvents.ShowSnackbar(
                                result.errorMessage ?: "An unknown error occurred."
                            )
                        )
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                        delay(1500)
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                interactions = result.data ?: emptyList()
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }


    private fun findRecordByPrompt(prompt: String) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.findInteractionsByPrompt(prompt).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update { it.copy(loading = false) }
                        _eventFlow.emit(
                            HistoryUiEvents.ShowSnackbar(
                                result.errorMessage ?: "An unknown error occurred."
                            )
                        )
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                        delay(1500)
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                interactions = result.data ?: emptyList()
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }

    fun deleteRecord(record: InteractionRecord) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.deleteInteractionRecord(record)
        }
    }
}