package com.interview.sample.domain.usecases

import com.interview.sample.domain.entities.LoginResponseEntity
import com.interview.sample.domain.repos.ILoginRepository
import com.interview.sample.network.resource.Resource
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val repository: ILoginRepository) {
    operator fun invoke(userName: String, password: String): Flow<Resource<LoginResponseEntity>> = repository.login(userName, password)
}