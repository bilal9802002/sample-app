package com.interview.sample.di

import com.interview.sample.data.db.dao.PatientDao
import com.interview.sample.data.db.dao.UserDao
import com.interview.sample.data.repos.LoginRepository
import com.interview.sample.data.repos.PatientRepository
import com.interview.sample.domain.repos.ILoginRepository
import com.interview.sample.domain.repos.IPatientRepository
import com.interview.sample.network.apis.ILoginApiService
import com.interview.sample.network.apis.IPatientApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesLoginRepository(
        apiService: ILoginApiService,
        userDao: UserDao
    ): ILoginRepository = LoginRepository(apiService, userDao)

    @Singleton
    @Provides
    fun providesPatientRepository(
        apiService: IPatientApiService,
        patientDao: PatientDao
    ): IPatientRepository = PatientRepository(apiService, patientDao)
}