package br.alexandregpereira.abacate

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.alexandregpereira.abacate.ui.component.AbacateCard
import br.alexandregpereira.abacate.ui.component.IMAGE_URL_DEFAULT
import br.alexandregpereira.abacate.ui.theme.AbacateTheme

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Window()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Window() = AbacateTheme {
    // A surface container using the 'background' color from the theme
    AbacateCard(
        actionHeaderText = "Anything asdsa asdasd asd asd asdasd as das dsa sa das",
        actionHeaderUrls = (0..10).map { IMAGE_URL_DEFAULT }
    )
}

@ExperimentalAnimationApi
@Preview
@Composable
fun DefaultPreview() {
    Window()
}