package com.example.cfhtask.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cfhtask.utils.RatingBar
import com.example.networkmodule.api.models.Movie

@Composable
fun MovieDetailsScreen(movie: Movie) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth().height(280.dp)) {
            MovieImage(movie.poster_path ?: "", modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(1.dp)))
        }
        Text(text = movie.title.toString(), fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.align(Alignment.Start))
        RatingBar(movie.vote_average ?: 0.0,modifier = Modifier.align(Alignment.Start))
        Text(text = movie.overview.toString(), fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.align(Alignment.Start))
    }
}