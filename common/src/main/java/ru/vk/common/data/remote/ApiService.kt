package ru.vk.common.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import ru.vk.common.data.remote.dto.AppDetailsDto
import ru.vk.common.data.remote.dto.AppDto

internal interface ApiService {
    @GET("catalog")
    suspend fun getApps(): List<AppDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}