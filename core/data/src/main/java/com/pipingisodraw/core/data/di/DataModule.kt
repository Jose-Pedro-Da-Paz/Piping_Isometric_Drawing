package com.pipingisodraw.core.data.di

import android.content.Context
import androidx.room.Room
import com.pipingisodraw.core.data.db.PipingIsoDatabase
import com.pipingisodraw.core.data.repository.ProjectRepositoryImpl
import com.pipingisodraw.core.domain.repository.ProjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PipingIsoDatabase =
        Room.databaseBuilder(
            context,
            PipingIsoDatabase::class.java,
            "piping_iso_draw.db"
        ).build()

    @Provides
    fun provideProjectDao(database: PipingIsoDatabase) = database.projectDao()

    @Provides
    fun providePlacedComponentDao(database: PipingIsoDatabase) = database.placedComponentDao()

    @Provides
    @Singleton
    fun provideProjectRepository(
        database: PipingIsoDatabase
    ): ProjectRepository = ProjectRepositoryImpl(
        projectDao = database.projectDao(),
        componentDao = database.placedComponentDao()
    )
}
