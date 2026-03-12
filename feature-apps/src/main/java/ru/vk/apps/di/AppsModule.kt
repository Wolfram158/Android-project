package ru.vk.apps.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.vk.apps.data.repository.AppsTestRepository
import ru.vk.apps.domain.repository.AppsRepository

@Module
@InstallIn(ViewModelComponent::class)
interface AppsModule {
    companion object {
        @ViewModelScoped
        @IoDispatcher
        @Provides
        fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
    }

    @Binds
    @ViewModelScoped
    fun bindAppsRepository(impl: AppsTestRepository): AppsRepository
}