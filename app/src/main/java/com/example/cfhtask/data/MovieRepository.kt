package com.example.cfhtask.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.cfhtask.data.local.MovieDao
import com.example.cfhtask.data.local.MovieEntity
import com.example.cfhtask.data.local.toMovieDomain
import com.example.cfhtask.data.local.toMovieEntity
import com.example.cfhtask.domain.MovieRepositoryInterface
import com.example.cfhtask.utils.NetworkUtils
import com.example.netoworklib.api.ApiService
import com.example.networkmodule.api.models.Movie
import com.example.networkmodule.api.models.PopularMoviesResponse
import com.example.networkmodule.api.models.toMovieDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlin.math.log

class MovieRepository(val api: ApiService, val localDB: MovieDao, val networkUtils: NetworkUtils) :
    MovieRepositoryInterface {
    override suspend fun getMoviesRemote(apiKey: String): PopularMoviesResponse =
        api.getPopularMovies(apiKey = apiKey)

    override suspend fun getMoviesLocal(): Flow<List<MovieEntity>> = localDB.getAllMovies()

    // get Single Source of Truth from data
   override suspend fun getMoviesSST(apiKey: String): List<Movie> {
       if (networkUtils.isConnected()) {
           // Fetch movies from remote source
           val remoteMovies = getMoviesRemote(apiKey).results
           // Convert remote DTOs to domain models
           val movieDomainList = remoteMovies.map { it.toMovieDomain() }
           // Insert movies into the local database
           val movieEntities =
               remoteMovies.map { it.toMovieEntity() }
           try {
               localDB.insertMovies(movieEntities)
           }catch (ex:Exception){
               Log.e("Error",ex.message.toString())
           }
           // Return the list of movies
         return  movieDomainList

       } else {
           return getMoviesLocal().first().map { it.toMovieDomain() }
       }
   }
}
