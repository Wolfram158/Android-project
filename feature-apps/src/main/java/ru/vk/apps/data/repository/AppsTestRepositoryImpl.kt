package ru.vk.apps.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import ru.vk.apps.data.mapper.AppDtoMapper
import ru.vk.apps.domain.model.AppsState
import ru.vk.apps.domain.repository.AppsRepository
import ru.vk.apps.test_data.TestData
import javax.inject.Inject

@ViewModelScoped
class AppsTestRepositoryImpl @Inject constructor(
    private val testData: TestData,
    private val appDtoMapper: AppDtoMapper
) : AppsRepository {
    private val signal = MutableSharedFlow<Unit>()
    private val appsStateFlow = MutableStateFlow<AppsState>(AppsState.Error)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAppsStateFlow(): Flow<AppsState> {
        return flowOf(
            appsStateFlow,
            getInitAppsLoadingFlow()
        )
            .flattenMerge()
    }

    override suspend fun initAppsLoading() {
        signal.emit(Unit)
    }

    private fun getInitAppsLoadingFlow(): Flow<AppsState> {
        return flow {
            signal.collect {
                appsStateFlow.update {
                    AppsState.Loading
                }
                delay(3000)
                appsStateFlow.update {
                    AppsState.Success(
                        appDtoMapper.mapAppDtosToDomains(testData.apps)
                    )
                }
            }
        }
    }
}