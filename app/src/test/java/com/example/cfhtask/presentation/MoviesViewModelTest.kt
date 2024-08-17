package com.example.cfhtask.presentation

import com.example.cfhtask.domain.PopularMoviesUseCase
import com.example.networkmodule.api.models.Movie
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val useCase = mockk<PopularMoviesUseCase>()
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MoviesViewModel(useCase)
    }
    @Test
    fun fetchPopularMoviesUseCase()= runTest {
        val expectedMovies = listOf(Movie(id = 1, title = "Test Movie", overview = "Description", vote_average = 4.5),
            Movie(id = 2, title = "Test Movie2", overview = "Description2", vote_average = 4.8)
        )
        coEvery { useCase.invoke() } returns expectedMovies

        viewModel.processIntents(MoviesListsIntents.FetchMovies)
        testDispatcher.scheduler.advanceUntilIdle()

        assertTrue(!viewModel.state.value.isLoading)
        assertEquals(expectedMovies, viewModel.state.value.data)

    }
    @Test
    fun fetchPopularMoviesUseCase_error()= runTest {
        coEvery { useCase.invoke() } throws Exception("Network error")
        viewModel.processIntents(MoviesListsIntents.FetchMovies)
        testDispatcher.scheduler.advanceUntilIdle()
        assertEquals("Network error", viewModel.state.value.exception)
    }
    @After
    fun tearDown() {
    }
}