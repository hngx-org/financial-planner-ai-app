package com.example.financial_planner_ai_app.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.financial_planner_ai_app.data.repository.DataStoreRepository
import com.example.financial_planner_ai_app.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.userPreferenceStore by preferencesDataStore(name = Constants.STORE_NAME)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun providesUserPreferenceStore(@ApplicationContext context: Context) =
        context.userPreferenceStore

    @Provides
    @Singleton
    fun providesDataStoreRepository(
        userPreferencesStore: DataStore<Preferences>
    ) = DataStoreRepository((userPreferencesStore))

}