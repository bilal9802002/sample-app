package com.interview.sample.di

import com.interview.sample.domain.repos.ILoginRepository
import com.interview.sample.domain.repos.IPatientRepository
import com.interview.sample.domain.usecases.LoginUseCase
import com.interview.sample.domain.usecases.PatientUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun providesLoginUseCase(repository: ILoginRepository) = LoginUseCase(repository)

    @Singleton
    @Provides
    fun providesPatientUseCase(repository: IPatientRepository) = PatientUseCase(repository)
}