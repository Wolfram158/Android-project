package ru.vk.apps.domain.model

import ru.vk.common.domain.Category

data class App(
    val id: String,
    val name: String,
    val category: Category,
    val iconUrl: String,
    val description: String
)
