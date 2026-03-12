package ru.vk.project.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.mmaltsev.vkeducation.presentation.appdetails.AppDetailsScreen
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
                onAppClick = {
                    navController.navigate(AppDetails)
                }
            )
        }

        composable<AppDetails> {
            AppDetailsScreen()
        }
    }
}