package ru.vk.common.data.remote

import ru.vk.common.data.remote.dto.AppDetailsDto
import ru.vk.common.data.remote.dto.AppDto

interface RemoteDataSource {
    suspend fun getApps(): List<AppDto>

    suspend fun getAppDetails(id: String): AppDetailsDto
}