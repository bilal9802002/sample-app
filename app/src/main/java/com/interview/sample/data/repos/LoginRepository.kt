package com.interview.sample.data.repos

import com.interview.sample.data.db.dao.UserDao
import com.interview.sample.domain.entities.LoginResponseEntity
import com.interview.sample.domain.entities.UserEntity
import com.interview.sample.domain.repos.ILoginRepository
import com.interview.sample.network.apis.ILoginApiService
import com.interview.sample.network.resource.Resource
import com.interview.sample.network.resource.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class LoginRepository(
    private val apiService: ILoginApiService,
    private val userDao: UserDao
) : ILoginRepository {
    override fun login(userName: String, password: String): Flow<Resource<LoginResponseEntity>> = networkBoundResource(
        query = { userDao.getUser().map { LoginResponseEntity(it.userId) } },
        fetch = {
            apiService.login()
        },
        saveFetchResult = {
            userDao.saveUser(UserEntity(it.userId, userName))
        }
    )
}