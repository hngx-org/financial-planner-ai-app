package com.example.financial_planner_ai_app.di

import android.content.Context
import androidx.room.Room
import com.example.financial_planner_ai_app.data.local.FinanceAIDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesFinanceAiDatabase(@ApplicationContext context: Context): FinanceAIDatabase {
        return Room.databaseBuilder(
            context,
            FinanceAIDatabase::class.java,
            "app_database"
        ).build()
    }
}