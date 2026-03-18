package ru.vk.apps.data.dto

import ru.vk.common.domain.Category

data class AppDto(
    val id: String,
    val name: String,
    val category: Category,
    val iconUrl: String,
    val description: String
)
