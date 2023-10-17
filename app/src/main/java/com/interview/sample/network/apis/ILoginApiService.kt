package com.interview.sample.network.apis

import com.interview.sample.data.models.LoginResponseModel
import retrofit2.http.GET

fun interface ILoginApiService {
    @GET("6042a2ed-d3e6-4483-b8e8-802e3b622f85")
    suspend fun login(): LoginResponseModel
}