package com.interview.sample.di

import com.interview.sample.network.apis.ILoginApiService
import com.interview.sample.network.apis.IPatientApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://run.mocky.io/v3/"

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesLoginApiService(retrofit: Retrofit): ILoginApiService = retrofit.create(
        ILoginApiService::class.java
    )

    @Singleton
    @Provides
    fun providesPatientApiService(retrofit: Retrofit): IPatientApiService = retrofit.create(
        IPatientApiService::class.java
    )
}