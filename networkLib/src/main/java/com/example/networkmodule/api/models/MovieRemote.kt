package com.example.networkmodule.api.models

import com.example.networkmodule.api.models.Movie

data class MovieRemote (
val adult: Boolean,
val backdrop_path: String,
val genre_ids: List<Int>,
val id: Int,
val original_language: String,
val original_title: String,
val overview: String,
val popularity: Double,
val poster_path: String,
val release_date: String,
val title: String,
val video: Boolean,
val vote_average: Double,
val vote_count: Int,
val isLocal: Boolean? = false
)
fun MovieRemote.toMovieDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        poster_path = "https://image.tmdb.org/t/p/w500/$poster_path", // Constructing the full image URL
        vote_average = vote_average,
        release_date = release_date,
        isLocal = isLocal ?: false
    )
}
