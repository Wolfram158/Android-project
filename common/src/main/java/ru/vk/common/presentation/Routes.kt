package ru.vk.common.presentation

import kotlinx.serialization.Serializable

@Serializable
object Apps

@Serializable
data class AppDetails(val id: String)