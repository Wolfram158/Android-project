package ru.vk.apps.domain.model

sealed interface AppsState {
    object Loading : AppsState

    object Error : AppsState

    class Success(
        val apps: List<App>
    ) : AppsState
}