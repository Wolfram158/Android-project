package ru.vk.common.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("category") val category: String,
    @SerialName("iconUrl") val iconUrl: String
)
