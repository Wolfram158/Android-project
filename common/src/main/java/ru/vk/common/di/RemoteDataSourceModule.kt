package ru.vk.common.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import ru.vk.common.data.remote.ApiService
import ru.vk.common.data.remote.RemoteDataSource
import ru.vk.common.data.remote.RemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RemoteDataSourceModule {
    @Binds
    @RetrofitDataSource
    fun bindRetrofitRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    companion object {
        @Provides
        @Singleton
        fun provideJson(): Json {
            return Json {
                ignoreUnknownKeys = true
            }
        }

        @Provides
        @Singleton
        fun provideRetrofit(json: Json): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://185.103.109.134")
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
        }

        @Provides
        @Singleton
        fun provideAppApi(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
}