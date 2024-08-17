package com.example.cfhtask.presentation

import com.example.networkmodule.api.models.Movie

data class MovieListsState(
    var isLoading: Boolean = true,
    var data: List<Movie> = emptyList(),
    var exception: String? = null
)
