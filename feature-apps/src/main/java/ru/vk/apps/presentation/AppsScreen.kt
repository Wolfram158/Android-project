package ru.vk.apps.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
    val events = viewModel.events
    val snackbar = remember { SnackbarHostState() }

    ObserveEvents(
        events,
        snackbar
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbar) }
    ) { paddingValues ->
        when (val value = appsState.value) {
            AppsState.Error -> Error({
                viewModel.initAppsLoading()
            })

            AppsState.Loading -> Loading()
            is AppsState.Success -> {
                AppsSuccessScreen(
                    value.apps,
                    {
                        onAppClick()
                    },
                    {
                        viewModel.clickAppLogo(it)
                    },
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
        }
    }
}