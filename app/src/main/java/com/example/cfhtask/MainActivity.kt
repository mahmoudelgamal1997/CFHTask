package com.example.cfhtask

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.example.cfhtask.presentation.MovieDetailsScreen
import com.example.cfhtask.presentation.MoviesViewModel
import com.example.cfhtask.presentation.PopularMoviesScreen
import com.example.cfhtask.ui.theme.CFHTaskTheme
import com.example.networkmodule.api.models.Movie
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CFHTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AppNavHost(modifier = Modifier.padding(it))
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), modifier: Modifier) {
    NavHost(navController = navController, startDestination = "movie_list") {
        composable("movie_list") {
            PopularMoviesScreen(navController)
        }

        composable("movie_details/{movieJson}") { backStackEntry ->
            val movieJson = backStackEntry.arguments?.getString("movieJson")
            val movie = Gson().fromJson(movieJson, Movie::class.java)
            MovieDetailsScreen(movie = movie)
        }
        }
    }



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CFHTaskTheme {
//            PopularMoviesScreen(modifier = Modifier.padding(12.dp))
    }
}