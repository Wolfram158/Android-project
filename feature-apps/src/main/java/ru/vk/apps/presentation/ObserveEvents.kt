package ru.vk.apps.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun ObserveEvents(
    events: Flow<AppsScreenEvents>,
    snackbar: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is AppsScreenEvents.AppLogoClicked -> {
                    snackbar.showSnackbar(event.name)
                }
            }
        }
    }
}