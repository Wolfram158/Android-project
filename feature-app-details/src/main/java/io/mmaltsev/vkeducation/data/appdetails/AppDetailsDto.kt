package io.mmaltsev.vkeducation.data.appdetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.vk.common.domain.Category

@Serializable
data class AppDetailsDto(
    val id: String,
    val name: String,
    val developer: String,
    val category: Category,
    val ageRating: Int,
    val size: Double,
    @SerialName("iconUrl")
    val icon: String,
    val screenshots: List<String>? = null,
    val description: String,
)