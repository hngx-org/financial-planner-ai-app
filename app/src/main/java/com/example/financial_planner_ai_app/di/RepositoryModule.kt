package com.example.financial_planner_ai_app.di

import android.content.Context
import com.example.financial_planner_ai_app.data.repository.AuthenticationRepo
import com.example.financial_planner_ai_app.data.repository.FormValidationRepo
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


}