package com.example.financial_planner_ai_app.data.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

val ON_BOARDING_KEY = booleanPreferencesKey(name = "on_boarding_key")

val LOGGED_IN_KEY = booleanPreferencesKey(name = "logged_in_key")

val USER_ID_KEY = stringPreferencesKey(name = "user_id_key")