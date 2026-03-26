package ru.vk.apps.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.vk.apps.domain.model.AppsState
import ru.vk.apps.domain.usecase.GetAppsStateFlowUseCase
import ru.vk.apps.domain.usecase.InitAppsLoadingUseCase
import ru.vk.common.di.IoDispatcher
import javax.inject.Inject

@HiltViewModel
class AppsViewModel @Inject constructor(
    getAppsStateFlowUseCase: GetAppsStateFlowUseCase,
    private val initAppsLoadingUseCase: InitAppsLoadingUseCase,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    val appsStateFlow = getAppsStateFlowUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            AppsState.Loading
        )

    init {
        initAppsLoading()
    }

    private val _events = Channel<AppsScreenEvents>(Channel.CONFLATED)
    val events = _events.receiveAsFlow()

    fun clickAppLogo(name: String) {
        viewModelScope.launch(ioDispatcher) {
            _events.send(AppsScreenEvents.AppLogoClicked(name))
        }
    }

    fun initAppsLoading() {
        viewModelScope.launch(ioDispatcher) {
            initAppsLoadingUseCase()
        }
    }
}