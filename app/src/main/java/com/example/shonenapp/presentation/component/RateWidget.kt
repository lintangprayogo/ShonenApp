package com.example.shonenapp.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import com.example.shonenapp.R


@Composable
fun FilledStar(
    starPath: Path,
    starPathBound: Rect,
    scaleFactor: Float = 2f
) {

    Canvas(modifier = Modifier.size(24.dp)) {
        val canvasSize = this.size
        scale(scaleFactor) {
            val left = (canvasSize.width / 2f) - (starPathBound.width / 1.7f)
            val top = (canvasSize.height / 2f) - (starPathBound.height / 1.7f)
            translate(left = left, top = top) {
                drawPath(
                    path = starPath,
                    color = Color.Yellow
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
        val canvasSize = this.size
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
                        color = Color.Yellow, size = Size(
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
        val canvasSize = this.size
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

@Preview(showBackground = true)
@Composable
fun PreviewFilledStarWidget() {
    val starPathString = stringResource(id = R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val startPathBounds = remember {
        starPath.getBounds()
    }
    FilledStar(starPath = starPath, starPathBound = startPathBounds)
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
