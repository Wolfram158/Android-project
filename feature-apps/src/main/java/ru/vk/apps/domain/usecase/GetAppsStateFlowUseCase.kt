package ru.vk.apps.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import ru.vk.apps.di.AppsRepositoryImplQualifier
import ru.vk.apps.domain.repository.AppsRepository
import javax.inject.Inject

@ViewModelScoped
class GetAppsStateFlowUseCase @Inject constructor(
    @param:AppsRepositoryImplQualifier private val repository: AppsRepository
) {
    operator fun invoke() = repository.getAppsStateFlow()
}