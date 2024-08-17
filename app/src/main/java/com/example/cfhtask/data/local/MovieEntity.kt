package com.example.cfhtask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.networkmodule.api.models.Movie
import com.example.networkmodule.api.models.MovieRemote

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val adult: Boolean?=null,
    val backdrop_path: String?=null,
    val original_language: String?=null,
    val original_title: String?=null,
    val overview: String?=null,
    val popularity: Double?=null,
    val poster_path: String?=null,
    val release_date: String?=null,
    val title: String?=null,
    val video: Boolean?=null,
    val vote_average: Double?=null,
    val vote_count: Int?=null
)

fun MovieEntity.toMovieDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        poster_path = "https://image.tmdb.org/t/p/w500/$poster_path", // Constructing the full image URL
        vote_average = vote_average,
        release_date = release_date,
        isLocal = true
    )
}

fun MovieRemote.toMovieEntity():MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        poster_path = "https://image.tmdb.org/t/p/w500/$poster_path", // Constructing the full image URL
        vote_average = vote_average,
        release_date = release_date,
    )
}