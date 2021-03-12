package br.alexandregpereira.abacate.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.alexandregpereira.abacate.ui.theme.AbacateTheme

@Composable
fun BottomBar() {
    val modifier = Modifier
        .height(56.dp)
        .fillMaxWidth()

    val itemCount = 4
    var itemIndex by remember { mutableStateOf(0f) }
    val itemIndexState by animateFloatAsState(targetValue = itemIndex)
    val radiusList = (0..itemCount).map { index ->
        animateDpAsState(targetValue = if (index.toFloat() == itemIndex) 24.dp else 0.dp).value
    }

    DrawBottomBar(
        itemCount = itemCount,
        itemIndex = itemIndexState,
        radiusList = radiusList,
        modifier
    )

    Row(modifier) {
        (0 until itemCount).forEach { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        itemIndex = index.toFloat()
                    }
            )
        }
    }
}

@Composable
private fun DrawBottomBar(
    itemCount: Int,
    itemIndex: Float,
    radiusList: List<Dp>,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier,
        onDraw = {
            val bottomY = size.height
            val itemWidth = size.width / itemCount
            val marginTop = 8.dp.toPx()
            val curveHeight = size.height * 0.78f + marginTop
            val itemCenter = itemWidth / 2
            val curveCenter = itemCenter + (itemWidth * itemIndex)

            radiusList.forEachIndexed { index, radius ->
                drawCircle(
                    Color.Green,
                    radius = radius.toPx(),
                    center = Offset(
                        itemCenter + (itemWidth * index),
                        (curveHeight / 2) - 2.dp.toPx()
                    )
                )
            }
            drawPath(
                path = Path().apply {
                    lineTo(0f, marginTop)
                    drawCircle(
                        marginTop = marginTop,
                        width = size.width,
                        curveHeight = curveHeight,
                        curveCenter = curveCenter,
                        convertToPx = { it.toPx() }
                    )
                    lineTo(size.width, marginTop)
                    lineTo(size.width, bottomY)
                    lineTo(0f, bottomY)
                    close()
                },
                color = Color.Green,
            )
        }
    )
}

private const val MAX_ITEMS = 5

private fun Path.drawCircle(
    marginTop: Float,
    width: Float,
    curveHeight: Float,
    curveCenter: Float,
    convertToPx: (Dp) -> Float
) {
    val minSize = width / MAX_ITEMS
    val minSizeCenter = minSize / 2

    val firstCurveStartPoint = Offset(
        curveCenter - minSizeCenter,
        marginTop
    )

    val point1 = Offset(
        convertToPx(12.dp) + (curveCenter - minSizeCenter),
        firstCurveStartPoint.y
    )

    val point2 = Offset(
        -convertToPx(8.dp) + (curveCenter - minSizeCenter),
        curveHeight
    )

    val point3 = Offset(curveCenter, curveHeight)

    val point4 = Offset(
        curveCenter + (curveCenter - point2.x),
        point2.y
    )

    val point5 = Offset(
        curveCenter + (curveCenter - point1.x),
        point1.y
    )

    val point6 = Offset(
        curveCenter + (curveCenter - firstCurveStartPoint.x),
        firstCurveStartPoint.y
    )


    lineTo(firstCurveStartPoint.x, firstCurveStartPoint.y)
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