package com.example.cfhtask.domain

import com.example.networkmodule.api.models.Movie

class PopularMoviesUseCase (val movieRepository: MovieRepositoryInterface) {

    suspend operator fun invoke(): List<Movie> {
        return movieRepository.getMoviesSST(com.example.cfhtask.BuildConfig.API_KEY)
    }
}