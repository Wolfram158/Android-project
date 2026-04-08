package ru.vk.apps.data.repository

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.vk.apps.domain.model.AppsState
import ru.vk.apps.domain.repository.AppsRepository

abstract class AppsStateHolder(
    initial: AppsState
) : AppsRepository {
    protected val signal = MutableSharedFlow<Unit>()
    protected val appsStateFlow = MutableStateFlow(initial)

    override suspend fun initAppsLoading() {
        signal.emit(Unit)
    }
}