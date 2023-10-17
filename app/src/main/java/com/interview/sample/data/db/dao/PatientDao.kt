package com.interview.sample.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.interview.sample.domain.entities.PatientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
    @Query("Select * from Patient where userId == :userId")
    fun getPatient(userId: String): Flow<PatientEntity>

    @Upsert
    suspend fun insertPatient(patientEntity: PatientEntity)
}