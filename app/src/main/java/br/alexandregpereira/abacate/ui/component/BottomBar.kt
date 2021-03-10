package br.alexandregpereira.abacate.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.alexandregpereira.abacate.ui.theme.AbacateTheme

@Composable
fun BottomBar() {
    val modifier = Modifier
        .height(40.dp)
        .fillMaxWidth()

    val itemCount = 6
    var animationData by remember { mutableStateOf(List(itemCount) { it == 0 }) }

    val curveInterpolatorList = animationData.map {
        animateFloatAsState(targetValue = if (it) 0.6f else 0f).value
    }

    DrawBottomBar(
        itemCount = itemCount,
        curveInterpolatorList = curveInterpolatorList,
        modifier
    )

    Row(modifier) {
        (0 until itemCount).forEach { index ->
            Icon(
                imageVector = Icons.Outlined.ArrowForward,
                tint = MaterialTheme.colors.primary,
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        animationData = List(itemCount) { it == index }
                    }
            )
        }
    }
}

@Composable
private fun DrawBottomBar(
    itemCount: Int,
    curveInterpolatorList: List<Float>,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier,
        onDraw = {
            val itemWidth = size.width / itemCount
            val itemCenter = itemWidth / 2
            val topY = 0f
            val bottomY = size.height
            val curveCentersAndRadiusList = curveInterpolatorList.mapIndexed { index, percentage ->
                (itemCenter + (itemWidth * index)) to bottomY * percentage
            }
//            Log.d(
//                "Canvas",
//                "index = $indexSelected($curveCenter, $curveCircleRadius) - lastIndexSelected = $lastIndexSelected($lastCurveCenter, $lastCurveCircleRadius)"
//            )
            drawPath(
                path = Path().apply {
                    curveCentersAndRadiusList.forEach {
                        drawCircle(
                            it.first, it.second
                        )
                    }
                    lineTo(size.width, topY)
                    lineTo(size.width, bottomY)
                    lineTo(0f, bottomY)
                    close()
                },
                color = Color.Green,
            )
        }
    )
}

private data class AnimationData(
    val lastWithCurve: Boolean = false,
    val withCurve: Boolean = true,
    val lastIndexSelected: Int = 0,
    val indexSelected: Int = 0
)

private fun Path.drawCircle(
    curveCenter: Float,
    curveCircleRadius: Float
) {
    val firstCurveStartPoint =
        Offset(curveCenter - (curveCircleRadius * 2) - (curveCircleRadius / 3), 0f)

    val point3 =
        Offset(curveCenter, curveCircleRadius + (curveCircleRadius / 4))

    val point6 =
        Offset(curveCenter + (curveCircleRadius * 2) + (curveCircleRadius / 3), 0f);

    val point1 = Offset(
        firstCurveStartPoint.x + curveCircleRadius + (curveCircleRadius / 4),
        firstCurveStartPoint.y
    )

    val point2 = Offset(
        point3.x - (curveCircleRadius * 2) + curveCircleRadius,
        point3.y
    )

    val point4 = Offset(
        point3.x + (curveCircleRadius * 2) - curveCircleRadius,
        point3.y
    )

    val point5 = Offset(
        point6.x - (curveCircleRadius + (curveCircleRadius / 4)),
        point6.y
    )

    lineTo(firstCurveStartPoint.x, firstCurveStartPoint.y);
    cubicTo(
        x1 = point1.x, y1 = point1.y,
        x2 = point2.x, y2 = point2.y,
        x3 = point3.x, y3 = point3.y
    )
    cubicTo(
        x1 = point4.x, y1 = point4.y,
        x2 = point5.x, y2 = point5.y,
        x3 = point6.x, y3 = point6.y
    )
}

@Preview
@Composable
fun BottomBarPreview() {
    AbacateTheme {
        Surface {
            BottomBar()
        }
    }
}