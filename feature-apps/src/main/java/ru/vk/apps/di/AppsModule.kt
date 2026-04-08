package ru.vk.apps.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.vk.apps.data.repository.AppsRepositoryImpl
import ru.vk.apps.data.repository.AppsTestRepositoryImpl
import ru.vk.apps.domain.repository.AppsRepository

@Module
@InstallIn(ViewModelComponent::class)
interface AppsModule {
    @Binds
    @AppsTestRepositoryQualifier
    fun bindAppsTestRepository(impl: AppsTestRepositoryImpl): AppsRepository

    @Binds
    @AppsRepositoryImplQualifier
    fun bindAppsRepositoryImpl(impl: AppsRepositoryImpl): AppsRepository
}