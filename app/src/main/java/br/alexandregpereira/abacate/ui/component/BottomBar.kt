package br.alexandregpereira.abacate.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.alexandregpereira.abacate.ui.theme.AbacateTheme

@Composable
fun BottomBar(
    items: List<ImageVector>
) {
    val modifier = Modifier
        .height(56.dp)
        .fillMaxWidth()

    val itemCount = items.size
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

    BottomBarItems(
        indexSelected = itemIndex.toInt(),
        items = items,
        onIndexSelected = { itemIndex = it.toFloat() },
        modifier = modifier
    )
}

@Composable
private fun BottomBarItems(
    indexSelected: Int,
    items: List<ImageVector>,
    onIndexSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val itemMarginTopList = (0..items.size).map { index ->
        animateDpAsState(targetValue = if (index == indexSelected) 0.dp else 8.dp).value
    }
    val itemMarginBottomList = (0..items.size).map { index ->
        animateDpAsState(targetValue = if (index == indexSelected) 8.dp else 0.dp).value
    }

    Row(modifier) {
        items.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false, radius = 40.dp)
                    ) {
                        onIndexSelected(index)
                    }
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(top = itemMarginTopList[index], bottom = itemMarginBottomList[index]),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = item,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun DrawBottomBar(
    itemCount: Int,
    itemIndex: Float,
    radiusList: List<Dp>,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
) {
    Canvas(
        modifier = modifier,
        onDraw = {
            val bottomY = size.height
            val itemWidth = size.width / itemCount
            val circleRadius = 24.dp.toPx()
            val marginTop = 8.dp.toPx()
            val curveHeight = circleRadius * 2 + marginTop / 2
            val itemCenter = itemWidth / 2
            val curveCenter = itemCenter + (itemWidth * itemIndex)

            radiusList.forEachIndexed { index, radius ->
                drawCircle(
                    color,
                    radius = radius.toPx(),
                    center = Offset(
                        itemCenter + (itemWidth * index),
                        size.height / 2 - marginTop / 2
                    )
                )
            }
            drawPath(
                path = Path().apply {
                    lineTo(0f, marginTop)
                    drawCircle(
                        marginTop = marginTop,
                        radius = circleRadius,
                        curveHeight = curveHeight,
                        curveCenter = curveCenter,
                    )
                    lineTo(size.width, marginTop)
                    lineTo(size.width, bottomY)
                    lineTo(0f, bottomY)
                    close()
                },
                color = color,
            )
        }
    )
}


private fun Path.drawCircle(
    marginTop: Float,
    radius: Float,
    curveHeight: Float,
    curveCenter: Float
) {
    val firstCurveStartPoint = Offset(
        curveCenter - radius * 1.5f,
        marginTop
    )

    val point1 = Offset(
        radius + (curveCenter - radius * 2),
        firstCurveStartPoint.y
    )

    val point2 = Offset(
        curveCenter - radius * 1.5f,
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
            BottomBar(
                listOf(
                    Icons.Outlined.Home,
                    Icons.Outlined.Home
                )
            )
        }
    }
}