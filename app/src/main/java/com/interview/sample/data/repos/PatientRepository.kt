package com.interview.sample.data.repos

import com.interview.sample.data.db.dao.PatientDao
import com.interview.sample.domain.entities.PatientEntity
import com.interview.sample.domain.repos.IPatientRepository
import com.interview.sample.network.apis.IPatientApiService
import com.interview.sample.network.resource.Resource
import com.interview.sample.network.resource.networkBoundResource
import kotlinx.coroutines.flow.Flow

class PatientRepository(
    private val apiService: IPatientApiService,
    private val patientDao: PatientDao
) : IPatientRepository {

    override fun patientData(userId: String): Flow<Resource<PatientEntity>> = networkBoundResource(
        query = {
            patientDao.getPatient(userId)
        },
        fetch = {
            apiService.patientData(userId)
        },
        saveFetchResult = {
            patientDao.insertPatient(it.copy(userId = userId))
        },
        shouldFetch = {
            it == null
        }
    )
}