package com.example.shonenapp.presentation.component

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.shonenapp.R
import com.example.shonenapp.ui.theme.StarColor
import com.example.shonenapp.utils.Constant.EMPTY_STAR
import com.example.shonenapp.utils.Constant.FILLED_STAR
import com.example.shonenapp.utils.Constant.HALF_STAR


@Composable
fun FilledStar(
    starPath: Path,
    starPathBound: Rect,
    scaleFactor: Float = 2f
) {

    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size
        scale(scaleFactor) {
            val left = (canvasSize.width / 2f) - (starPathBound.width / 1.7f)
            val top = (canvasSize.height / 2f) - (starPathBound.height / 1.7f)
            translate(left = left, top = top) {
                drawPath(
                    path = starPath,
                    color = StarColor
                )
            }
        }


    }
}

@Composable
fun HalfFilledStar(
    starPath: Path,
    starPathBound: Rect,
    scaleFactor: Float = 2f
) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size
        scale(scaleFactor) {
            val left = (canvasSize.width / 2f) - (starPathBound.width / 1.7f)
            val top = (canvasSize.height / 2f) - (starPathBound.height / 1.7f)
            translate(left = left, top = top) {
                drawPath(
                    path = starPath,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
                clipPath(starPath) {
                    drawRect(
                        color = StarColor, size = Size(
                            width = starPathBound.width / 1.7f,
                            height = starPathBound.height * scaleFactor,
                        )
                    )
                }
            }
        }


    }
}


@Composable
fun EmptyStar(
    starPath: Path,
    starPathBound: Rect,
    scaleFactor: Float = 2f
) {

    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = size
        scale(scaleFactor) {
            val left = (canvasSize.width / 2f) - (starPathBound.width / 1.7f)
            val top = (canvasSize.height / 2f) - (starPathBound.height / 1.7f)
            translate(left = left, top = top) {
                drawPath(
                    path = starPath,
                    color = Color.LightGray.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
fun calculateRating(rating: Double): Map<String, Int> {
    val maxStars by remember { mutableStateOf(5) }
    var filledStars by remember { mutableStateOf(0) }
    var halfStars by remember { mutableStateOf(0) }
    var emptyStars by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = rating) {
        val (firstNumber, lastnumber) = rating.toString().split(".").map {
            it.toInt()
        }

        if (firstNumber in 0..5 && lastnumber in 0..9) {
            filledStars = firstNumber

            when (lastnumber) {
                in 1..5 -> {
                    halfStars++
                }
                in 6..9 -> {
                    filledStars++
                }
                else -> {
                    halfStars = 0
                }
            }

            if (firstNumber == 5 && lastnumber > 0) {
                filledStars = 5
                halfStars = 0
                emptyStars = 0
            }
        } else {
            Log.d("Rating Widget", "invalid rating")
        }
    }
    emptyStars = maxStars - (halfStars + filledStars)
    return mapOf(
        FILLED_STAR to filledStars,
        HALF_STAR to halfStars,
        EMPTY_STAR to emptyStars
    )
}

@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    scaleFactor: Float = 3f,
    spaceBetween: Dp = 6.dp
) {
    val mapStar = calculateRating(rating = rating)
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val startPathBound = remember {
        starPath.getBounds()
    }

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(spaceBetween)) {
        val filledStar = mapStar[FILLED_STAR] ?: 0
        val halfStar = mapStar[HALF_STAR] ?: 0
        val emptyStar = mapStar[EMPTY_STAR] ?: 0

        repeat(filledStar) {
            FilledStar(
                starPath = starPath,
                starPathBound = startPathBound,
                scaleFactor = scaleFactor
            )
        }
        repeat(halfStar) {
            HalfFilledStar(
                starPath = starPath,
                starPathBound = startPathBound,
                scaleFactor = scaleFactor
            )
        }
        repeat(emptyStar) {
            EmptyStar(
                starPath = starPath,
                starPathBound = startPathBound,
                scaleFactor = scaleFactor
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFilledStarWidget() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val startPathBound = remember {
        starPath.getBounds()
    }
    FilledStar(starPath = starPath, starPathBound = startPathBound)
}

@Preview(showBackground = true)
@Composable
fun PreviewHalfFilledStar() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val startPathBounds = remember {
        starPath.getBounds()
    }
    HalfFilledStar(starPath = starPath, starPathBound = startPathBounds)
}

@Preview(showBackground = true)
@Composable
fun PreviewEmptyStar() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val startPathBounds = remember {
        starPath.getBounds()
    }
    EmptyStar(starPath = starPath, starPathBound = startPathBounds)
}


@Preview(showBackground = true)
@Composable
fun PreviewRateWidget(
) {
    RatingWidget(modifier = Modifier, rating = 4.5)
}