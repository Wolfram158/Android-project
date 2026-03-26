package ru.vk.common.data.remote

import ru.vk.common.data.remote.dto.AppDetailsDto
import ru.vk.common.data.remote.dto.AppDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {
    override suspend fun getApps(): List<AppDto> {
        return apiService.getApps()
    }

    override suspend fun getAppDetails(id: String): AppDetailsDto {
        return apiService.getAppDetails(id)
    }
}