package com.lge.secondapp.model

import com.google.gson.annotations.SerializedName
import java.util.*



// JSON => DTO
//     Gson(Google + JSON) - KVC
data class GithubUser(
    val login: String,
    val id: Int,
    val name: String?,
    val location: String?,
    val company: String?,
    val blog: String?,
    @field:SerializedName("avatar_url") val avatarUrl: String,
    @field:SerializedName("created_at") val createdAt: Date,
    @field:SerializedName("updated_at") val updatedAt: Date
)
