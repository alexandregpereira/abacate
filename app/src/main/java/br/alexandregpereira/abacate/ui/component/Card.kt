package br.alexandregpereira.abacate.ui.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.alexandregpereira.abacate.ui.theme.AbacateTheme

@ExperimentalAnimationApi
@Composable
fun AbacateCard(
    actionHeaderText: String,
    actionHeaderUrls: List<String>,
    postText: String,
    modifier: Modifier = Modifier,
) {
    Card(modifier.padding(all = 8.dp)) {
        Column {
            ActionHeader(
                text = actionHeaderText,
                urls = actionHeaderUrls,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            PostText(
                text = postText,
                modifier = Modifier.padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Preview
@ExperimentalAnimationApi
@Composable
fun AbacateCardPreview() {
    AbacateTheme {
        AbacateCard(
            actionHeaderText = "Anything asdsa asdasd asd asd asdasd as das dsa sa das",
            actionHeaderUrls = (0..10).map { IMAGE_URL_DEFAULT },
            postText = "Aasdas asd asd asdasdasdas asddasdasd"
        )
    }
}