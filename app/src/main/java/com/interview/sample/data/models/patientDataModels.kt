package com.interview.sample.data.models

import com.google.gson.annotations.SerializedName
import com.interview.sample.domain.entities.AssociatedDrugEntity
import com.interview.sample.domain.entities.LabEntity
import com.interview.sample.domain.entities.MedicationEntity
import com.interview.sample.domain.entities.PatientEntity
import com.interview.sample.domain.entities.ProblemEntity

data class AssociatedDrugModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("dose")
    val dose: String,
    @SerializedName("strength")
    val strength: String
): Mapper<AssociatedDrugEntity> {
    override fun mapToEntity() = AssociatedDrugEntity(
        name = name, dose = dose, strength = strength
    )
}


data class MedicationModel(
    @SerializedName("type")
    val type: String,
    @SerializedName("associatedDrugs")
    val associatedDrugs: List<AssociatedDrugModel>
): Mapper<MedicationEntity> {
    override fun mapToEntity() = MedicationEntity(
        type = type, associatedDrugs = associatedDrugs.map { it.mapToEntity() }
    )
}

data class LabModel(
    @SerializedName("missing_field")
    val testName: String
): Mapper<LabEntity> {
    override fun mapToEntity() = LabEntity(
        testName = testName
    )
}

data class ProblemModel(
    @SerializedName("type")
    val type: String,
    @SerializedName("medications")
    val medications: List<MedicationModel>,
    @SerializedName("labs")
    val labs: List<LabModel>
): Mapper<ProblemEntity> {
    override fun mapToEntity() = ProblemEntity(
        type = type, medications = medications.map { it.mapToEntity() }, labs = labs.map { it.mapToEntity() }
    )
}

data class PatientModel(
    @SerializedName("problems")
    val problems: List<ProblemModel>
): Mapper<PatientEntity> {
    override fun mapToEntity() = PatientEntity(
        problems = problems.map { it.mapToEntity() }
    )
}

fun interface Mapper<Entity> {
    fun mapToEntity(): Entity
}