package ru.vk.apps.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import ru.vk.apps.data.mapper.AppDtoMapper
import ru.vk.apps.domain.model.AppsState
import ru.vk.common.data.remote.RemoteDataSource
import ru.vk.common.di.RetrofitDataSource
import javax.inject.Inject

@ViewModelScoped
class AppsRepositoryImpl @Inject constructor(
    @param:RetrofitDataSource private val remoteDataSource: RemoteDataSource,
    private val appDtoMapper: AppDtoMapper
) : AppsStateHolder(AppsState.Loading) {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAppsStateFlow(): Flow<AppsState> {
        return flowOf(
            appsStateFlow,
            getInitAppsLoadingFlow()
        )
            .flattenMerge()
    }

    private fun getInitAppsLoadingFlow(): Flow<AppsState> {
        return flow {
            signal.collect {
                appsStateFlow.update {
                    AppsState.Loading
                }
                appsStateFlow.update {
                    try {
                        AppsState.Success(
                            appDtoMapper.mapAppDtosToDomains(remoteDataSource.getApps())
                        )
                    } catch (_: Exception) {
                        AppsState.Error
                    }
                }
            }
        }
    }
}