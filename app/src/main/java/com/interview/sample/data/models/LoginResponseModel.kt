package com.interview.sample.data.models

import com.google.gson.annotations.SerializedName
import com.interview.sample.domain.entities.LoginResponseEntity

data class LoginResponseModel(
    @SerializedName("userId")
    val userId: String
) : Mapper<LoginResponseEntity> {
    override fun mapToEntity(): LoginResponseEntity = LoginResponseEntity(userId)
}