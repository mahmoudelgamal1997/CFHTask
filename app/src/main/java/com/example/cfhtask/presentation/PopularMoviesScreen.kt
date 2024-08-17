package com.example.cfhtask.presentation

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.example.networkmodule.api.models.Movie
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel
import java.io.File


  @Composable
  fun PopularMoviesScreen(navController: NavHostController, viewModel: MoviesViewModel =  koinViewModel()) {
        // Call fetchPopularMovies() to load data
        LaunchedEffect(Unit) {
            viewModel.processIntents(MoviesListsIntents.FetchMovies)
        }

        // Observe state from ViewModel
        val moviesState = viewModel.state.collectAsState()
      if (moviesState.value.isLoading) {
          Box(
              modifier = Modifier
                  .fillMaxSize() // Fill the available space
                  .background(Color.White) // Optional: Set a background color
          ) {
              CircularProgressIndicator(
                  modifier = Modifier
                      .align(Alignment.Center) // Center the indicator
              )
          }
      } else {

        // UI layout
        Surface(modifier = Modifier.padding(16.dp), color = MaterialTheme.colorScheme.background) {
            LazyColumn (modifier = Modifier.fillMaxSize()){
                // Display your list of movies
                items(moviesState.value.data){movie->
                    MovieItem(movie = movie,navController)
                }
            }}
        }
    }

    @Composable
    fun MovieItem(movie: Movie,navController: NavController) {
        Row(
            modifier = Modifier
                .clickable {
                    val movieJson = Uri.encode(Gson().toJson(movie))
                    navController.navigate("movie_details/$movieJson")
                }
                .height(100.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            MovieImage(movie.poster_path ?: "" , modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp)))

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = movie.title ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Rating: ${movie.vote_average}",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }

    @Composable
    fun MovieImage(posterPath: String,modifier: Modifier) {
        val context = LocalContext.current
        val imageLoader = ImageLoader.Builder(context)
            .crossfade(true)
            .diskCache {
                DiskCache.Builder().maxSizePercent(.03)
                    .directory(File(context.cacheDir, "coil_cache"))
                    .build()
            }.respectCacheHeaders(false)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(.1)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED).logger(DebugLogger())
            .build()

        Image(
            painter = rememberAsyncImagePainter(model = "https://image.tmdb.org/t/p/w500${posterPath}",imageLoader,),
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    }

