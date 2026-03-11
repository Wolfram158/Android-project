package ru.vk.apps.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ru.vk.apps.domain.model.AppsState
import ru.vk.common.presentation.Error
import ru.vk.common.presentation.Loading

@Composable
fun AppsScreen(
    onAppClick: () -> Unit
) {
    val viewModel = hiltViewModel<AppsViewModel>()
    val appsState = viewModel.appsStateFlow.collectAsState()

    when (val value = appsState.value) {
        AppsState.Error -> Error({
            viewModel.initAppsLoading()
        })

        AppsState.Loading -> Loading()
        is AppsState.Success -> {
            AppsSuccessScreen(value.apps) {
                onAppClick()
            }
        }
    }
}