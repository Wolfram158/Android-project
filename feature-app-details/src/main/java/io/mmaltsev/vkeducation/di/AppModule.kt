package io.mmaltsev.vkeducation.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.mmaltsev.vkeducation.data.appdetails.AppDetailsMapper
import io.mmaltsev.vkeducation.data.appdetails.AppDetailsRepositoryImpl
import io.mmaltsev.vkeducation.data.appdetails.local.AppDatabase
import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsDao
import io.mmaltsev.vkeducation.data.appdetails.local.AppDetailsEntityMapper
import io.mmaltsev.vkeducation.domain.appdetails.AppDetailsRepository
import io.mmaltsev.vkeducation.domain.appdetails.GetAppDetailsUseCase
import io.mmaltsev.vkeducation.domain.appdetails.ToggleWishlistUseCase
import ru.vk.common.data.remote.RemoteDataSource
import ru.vk.common.di.RetrofitDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    @Provides
//    @Singleton
//    fun provideAppDetailsRepository(appApi: AppApi): AppDetailsRepository {
//        return AppDetailsRepositoryImpl(appApi)
//    }

    @Provides
    @Singleton
    fun provideGetAppDetailsUseCase(repository: AppDetailsRepository): GetAppDetailsUseCase {
        return GetAppDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleWishlistUseCase(repository: AppDetailsRepository): ToggleWishlistUseCase {
        return ToggleWishlistUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).addMigrations(
            object : Migration(1, 2) {
                override fun migrate(db: SupportSQLiteDatabase) {
                    db.execSQL("alter table ${AppDatabase.APP_DETAILS_TABLE} add column isInWishlist integer not null default 0")
                }
            }
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDetailsDao(database: AppDatabase): AppDetailsDao {
        return database.appDetailsDao()
    }

    @Provides
    @Singleton
    fun provideAppDetailsEntityMapper(): AppDetailsEntityMapper {
        return AppDetailsEntityMapper()
    }

    @Provides
    @Singleton
    fun provideAppDetailsMapper(): AppDetailsMapper {
        return AppDetailsMapper()
    }

    @Provides
    @Singleton
    fun provideAppDetailsRepository(
        @RetrofitDataSource api: RemoteDataSource,
        dao: AppDetailsDao,
        mapper: AppDetailsMapper,
        entityMapper: AppDetailsEntityMapper
    ): AppDetailsRepository {
        return AppDetailsRepositoryImpl(api, dao, mapper, entityMapper)
    }
}