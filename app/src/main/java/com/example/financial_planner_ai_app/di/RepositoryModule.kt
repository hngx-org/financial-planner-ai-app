package com.example.financial_planner_ai_app.di

import com.example.financial_planner_ai_app.data.repository.AuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAuthenticationRepository(authenticationRepository: AuthenticationRepository): AuthenticationRepository

}