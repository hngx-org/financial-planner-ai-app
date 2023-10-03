package com.example.financial_planner_ai_app.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.financial_planner_ai_app.data.local.LOGGED_IN_KEY
import com.example.financial_planner_ai_app.data.local.ON_BOARDING_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreRepository(
    private val userPreferenceStore: DataStore<Preferences>
) {

    suspend fun saveOnboardingState(onBoarded: Boolean) {
        userPreferenceStore.edit { preferences ->
            preferences[ON_BOARDING_KEY] = onBoarded
        }
    }

    fun readOnboardingState(): Flow<Boolean> {
        return userPreferenceStore.data
            .catch { exception ->
                if (exception is Exception) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[ON_BOARDING_KEY] ?: false
            }
    }

    suspend fun saveLoggedInStatus(loggedIn: Boolean) {
        userPreferenceStore.edit { preferences ->
            preferences[LOGGED_IN_KEY] = loggedIn
        }
    }

    fun readLoggedInStatus(): Flow<Boolean> {
        return userPreferenceStore.data
            .catch { exception ->
                if (exception is Exception) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[LOGGED_IN_KEY] ?: false
            }
    }
}