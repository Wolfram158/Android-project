package ru.vk.apps.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.vk.apps.domain.model.AppsState

interface AppsRepository {
    fun getAppsStateFlow(): Flow<AppsState>

    suspend fun initAppsLoading()
}