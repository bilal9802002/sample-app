package com.interview.sample.ui.args

import com.google.gson.Gson
import com.interview.sample.domain.entities.AssociatedDrugEntity
import com.interview.sample.ui.common.JsonNavType

class DrugDetailScreenArgs: JsonNavType<AssociatedDrugEntity>() {
    override fun fromJsonParse(value: String): AssociatedDrugEntity {
        return Gson().fromJson(value, AssociatedDrugEntity::class.java)
    }

    override fun AssociatedDrugEntity.getJsonParse(): String {
        return Gson().toJson(this)
    }
}