package ru.vk.project.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import io.mmaltsev.vkeducation.presentation.appdetails.AppDetailsScreen
import io.mmaltsev.vkeducation.presentation.appdetails.AppDetailsViewModel
import ru.vk.apps.presentation.AppsScreen
import ru.vk.common.presentation.AppDetails
import ru.vk.common.presentation.Apps

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Apps
    ) {
        composable<Apps> {
            AppsScreen(
                onAppClick = { id ->
                    navController.navigate(AppDetails(id))
                }
            )
        }

        composable<AppDetails> { entry ->
            val id = entry.toRoute<AppDetails>().id
            val viewModel = hiltViewModel<AppDetailsViewModel>()
            viewModel.updateId(id)
            AppDetailsScreen(
                viewModel,
                {
                    viewModel.retry()
                }
            )
        }
    }
}