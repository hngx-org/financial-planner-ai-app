package com.example.financial_planner_ai_app.di

import android.content.Context
import com.example.financial_planner_ai_app.data.local.FinanceAIDatabase
import com.example.financial_planner_ai_app.data.repository.AuthenticationRepo
import com.example.financial_planner_ai_app.data.repository.DatabaseRepository
import com.example.financial_planner_ai_app.data.repository.FormValidationRepo
import com.example.financial_planner_ai_app.data.repository.OpenAiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesAuthRepo(@ApplicationContext context: Context) = AuthenticationRepo(context)

    @Singleton
    @Provides
    fun providesFormValidationRepo() = FormValidationRepo()

    @Singleton
    @Provides
    fun providesOpenAiRepository() = OpenAiRepository()

    @Singleton
    @Provides
    fun providesDatabaseRepository(financeAIDatabase: FinanceAIDatabase) = DatabaseRepository(financeAIDatabase)

}