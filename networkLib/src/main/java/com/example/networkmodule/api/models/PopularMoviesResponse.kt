package com.example.networkmodule.api.models

data class PopularMoviesResponse(
    val page: Int,
    val results: List<MovieRemote>,
    val total_pages: Int,
    val total_results: Int
)