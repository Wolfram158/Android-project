package ru.vk.apps.domain.model

data class App(
    val id: String,
    val name: String,
    val category: String,
    val iconUrl: String,
    val description: String
)
