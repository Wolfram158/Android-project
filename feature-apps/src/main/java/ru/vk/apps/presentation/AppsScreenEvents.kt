package ru.vk.apps.presentation

sealed interface AppsScreenEvents {
    class AppLogoClicked(val name: String) : AppsScreenEvents
}