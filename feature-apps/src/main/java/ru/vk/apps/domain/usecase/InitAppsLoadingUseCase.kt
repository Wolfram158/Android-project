package ru.vk.apps.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import ru.vk.apps.domain.repository.AppsRepository
import javax.inject.Inject

@ViewModelScoped
class InitAppsLoadingUseCase @Inject constructor(
    private val repository: AppsRepository
) {
    suspend operator fun invoke() = repository.initAppsLoading()
}