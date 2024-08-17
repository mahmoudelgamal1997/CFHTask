package com.example.cfhtask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cfhtask.domain.PopularMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(private val popularMoviesUseCase: PopularMoviesUseCase) : ViewModel() {
    private val _state = MutableStateFlow<MovieListsState>(MovieListsState(isLoading = true))
    val state: StateFlow<MovieListsState> = _state

   private fun fetchPopularMovies() {
        _state.value = MovieListsState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = popularMoviesUseCase.invoke()
                _state.value = MovieListsState(isLoading = false, data = response)

            } catch (e: Exception) {
                // Handle the error
                _state.value = MovieListsState(isLoading = false, exception = e.message)
            }
        }
    }

    fun processIntents(intents: MoviesListsIntents) {
        when (intents) {
            is MoviesListsIntents.FetchMovies -> fetchPopularMovies()
        }
    }
}