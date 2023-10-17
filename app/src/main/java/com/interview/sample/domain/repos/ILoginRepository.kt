package com.interview.sample.domain.repos

import com.interview.sample.domain.entities.LoginResponseEntity
import com.interview.sample.network.resource.Resource
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {
    fun login(userName: String, password: String): Flow<Resource<LoginResponseEntity>>
}