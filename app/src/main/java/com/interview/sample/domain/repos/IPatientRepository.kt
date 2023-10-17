package com.interview.sample.domain.repos

import com.interview.sample.domain.entities.PatientEntity
import com.interview.sample.network.resource.Resource
import kotlinx.coroutines.flow.Flow

interface IPatientRepository {
    fun patientData(userId: String): Flow<Resource<PatientEntity>>
}