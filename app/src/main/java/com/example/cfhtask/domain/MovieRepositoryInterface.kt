package com.example.cfhtask.domain

import androidx.lifecycle.LiveData
import com.example.cfhtask.data.local.MovieEntity
import com.example.networkmodule.api.models.Movie
import com.example.networkmodule.api.models.MovieRemote
import com.example.networkmodule.api.models.PopularMoviesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MovieRepositoryInterface {
    suspend fun getMoviesRemote(apiKey: String): PopularMoviesResponse

    suspend fun getMoviesLocal(): Flow<List<MovieEntity>>

    suspend fun getMoviesSST(apiKey: String): List<Movie>
}