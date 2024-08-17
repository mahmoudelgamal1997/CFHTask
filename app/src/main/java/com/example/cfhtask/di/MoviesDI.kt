package com.example.cfhtask.di

import androidx.room.Room
import com.example.cfhtask.data.MovieRepository
import com.example.cfhtask.data.local.AppDatabase
import com.example.cfhtask.domain.MovieRepositoryInterface
import com.example.cfhtask.domain.PopularMoviesUseCase
import com.example.cfhtask.presentation.MoviesViewModel
import com.example.cfhtask.utils.NetworkUtils
import org.koin.core.scope.get
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.get

val repositoryModule = module {
        single<MovieRepositoryInterface> { MovieRepository(get(),get(),get()) }
        single { NetworkUtils(get()) }
    }
    val viewModelModule = module {
        single { MoviesViewModel(get()) }
    }

    val movieUseCaseModule = module {
        single { PopularMoviesUseCase(get()) }

    }

    val databaseBuilder = module {
        single { Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "movies_database"
        ).build()
        }

        single {
        get<AppDatabase>().movieDao()
    }
    }
