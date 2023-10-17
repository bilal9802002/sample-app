package com.interview.sample.domain.usecases

import com.interview.sample.domain.entities.PatientEntity
import com.interview.sample.domain.repos.IPatientRepository
import com.interview.sample.network.resource.Resource
import kotlinx.coroutines.flow.Flow

class PatientUseCase(private val repository: IPatientRepository) {
    operator fun invoke(userId: String): Flow<Resource<PatientEntity>> = repository.patientData(userId)
}