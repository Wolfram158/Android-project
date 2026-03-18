package ru.vk.apps.data.mapper

import dagger.hilt.android.scopes.ViewModelScoped
import ru.vk.apps.data.dto.AppDto
import ru.vk.apps.domain.model.App
import javax.inject.Inject

@ViewModelScoped
class AppDtoMapper @Inject constructor() {
    fun mapAppDtoToDomain(appDto: AppDto) = with(appDto) {
        App(
            id = id,
            name = name,
            category = category,
            iconUrl = iconUrl,
            description = description
        )
    }

    fun mapAppDtosToDomains(appDtos: List<AppDto>) = appDtos.map { mapAppDtoToDomain(it) }
}