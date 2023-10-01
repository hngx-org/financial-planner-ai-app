package com.example.financial_planner_ai_app.di

import com.example.financial_planner_ai_app.data.repository.AuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Binds
    fun provideAuthenticationRepository(authenticationRepository: AuthenticationRepository): AuthenticationRepository {
        return authenticationRepository
    }
}