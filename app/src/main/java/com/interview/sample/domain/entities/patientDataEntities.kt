package com.interview.sample.domain.entities

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class AssociatedDrugEntity(
    val name: String,
    val dose: String,
    val strength: String
) {
    override fun toString(): String {
        return Uri.encode(Gson().toJson(this))
    }
}

data class MedicationEntity(
    val type: String,
    val associatedDrugs: List<AssociatedDrugEntity>
)

data class LabEntity(
    val testName: String
)

@Entity("Problem")
data class ProblemEntity(
    val type: String,
    val medications: List<MedicationEntity>,
    val labs: List<LabEntity>
)

@Entity("Patient")
data class PatientEntity(
    @PrimaryKey
    @ColumnInfo("userId")
    val userId: String = "",
    @ColumnInfo("problems")
    val problems: List<ProblemEntity>
)

class ProblemEntityTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun toString(problems: List<ProblemEntity>): String {
        return gson.toJson(problems)
    }

    @TypeConverter
    fun toEntity(jsonString: String): List<ProblemEntity> {
        val objectType = object : TypeToken<List<ProblemEntity>>() {}.type
        return gson.fromJson(jsonString, objectType)
    }
}