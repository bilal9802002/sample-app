package com.interview.sample.network.apis

import com.interview.sample.data.models.PatientModel
import retrofit2.http.GET
import retrofit2.http.Query

fun interface IPatientApiService {
    @GET("00deb853-9d2e-4fdb-a36f-3ecd19a08bf3")
    suspend fun patientData(@Query("userId") userId: String): PatientModel
}