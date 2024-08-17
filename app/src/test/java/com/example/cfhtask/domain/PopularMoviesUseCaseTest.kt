package com.example.cfhtask.domain

import com.example.networkmodule.api.models.Movie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PopularMoviesUseCaseTest {
    private lateinit var popularMoviesUseCase: PopularMoviesUseCase
    private lateinit var moviesRepository: MovieRepositoryInterface
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        moviesRepository = mockk<MovieRepositoryInterface> ()
        popularMoviesUseCase = mockk<PopularMoviesUseCase> ()
    }

    @Test
    fun fetchPopularMovies(){
        val expectedMovies = listOf(
            Movie(id = 1, title = "Test Movie", overview = "Description", vote_average = 4.5),
            Movie(id = 2, title = "Test Movie2", overview = "Description2", vote_average = 4.8)
        )
        coEvery { popularMoviesUseCase.invoke() } returns expectedMovies
    }

    @Test
    fun fetchPopularMovies_empty() = runTest{
        val expectedMovies = emptyList<Movie>()
        coEvery {  popularMoviesUseCase.invoke() } returns expectedMovies
        val actualMovies = popularMoviesUseCase.invoke()
        assertEquals(expectedMovies, actualMovies)
    }
}