package io.mmaltsev.vkeducation.presentation.appdetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.mmaltsev.vkeducation.domain.appdetails.GetAppDetailsUseCase
import io.mmaltsev.vkeducation.domain.appdetails.ToggleWishlistUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vk.common.di.IoDispatcher
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
    private val toggleWishlistUseCase: ToggleWishlistUseCase,
    private val savedStateHandle: SavedStateHandle,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val savedId: StateFlow<String?> = savedStateHandle.getStateFlow(
        key = APP_DETAILS_KEY,
        initialValue = null
    )

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppDetailsEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        observeAppDetails()
    }

    private fun observeAppDetails() {
        viewModelScope.launch(ioDispatcher) {
            savedId.collect { id ->
                if (id != null) {
                    getAppDetails(id)
                } else {
                    _state.value = AppDetailsState.Loading
                }
            }
        }
    }

    fun showUnderDevelopmentMessage() {
        viewModelScope.launch {
            _events.send(AppDetailsEvent.UnderDevelopment)
        }
    }

    fun collapseDescription() {
        _state.update { currentState ->
            if (currentState is AppDetailsState.Content) {
                currentState.copy(descriptionCollapsed = true)
            } else {
                currentState
            }
        }
    }

    private fun getAppDetails(id: String) {
        viewModelScope.launch {
            _state.value = AppDetailsState.Loading

            getAppDetailsUseCase(id).catch { e ->
                _state.value = AppDetailsState.Error
                Log.d("HOHOHO", "ERROR $e")
            }.collect { appDetails ->
                _state.value = AppDetailsState.Content(
                    appDetails = appDetails,
                    descriptionCollapsed = false
                )
            }
        }
    }

    fun toggleWishlist() {
        viewModelScope.launch {
            savedStateHandle.get<String>(APP_DETAILS_KEY)?.let { id ->
                toggleWishlistUseCase(id)
            }
        }
    }

    fun updateId(id: String) {
        savedStateHandle[APP_DETAILS_KEY] = id
    }

    fun retry() {
        savedStateHandle.get<String>(APP_DETAILS_KEY)?.let { id ->
            getAppDetails(id)
        }
    }

    companion object {
        private const val APP_DETAILS_KEY = "app_details_key"
    }
}