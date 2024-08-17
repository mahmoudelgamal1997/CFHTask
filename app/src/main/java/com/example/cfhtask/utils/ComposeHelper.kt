package com.example.cfhtask.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
    starSize: Dp = 24.dp,
    starColor: Color = Color.Yellow
) {
    Row(modifier = modifier) {
        val filledStars = rating.toInt()
        val halfStar = (rating - filledStars) >= 0.5
        val emptyStars = maxRating - filledStars - if (halfStar) 1 else 0

            repeat(filledStars) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    modifier = Modifier.size(starSize),
                    tint = starColor
                )
            }

        if (halfStar) {
            Icon(
                imageVector = Icons.Filled.StarHalf,
                contentDescription = null,
                modifier = Modifier.size(starSize),
                tint = starColor
            )
        }

        repeat(emptyStars) {
            Icon(
                imageVector = Icons.Filled.StarBorder,
                contentDescription = null,
                modifier = Modifier.size(starSize),
                tint = starColor
            )
        }
    }
}
