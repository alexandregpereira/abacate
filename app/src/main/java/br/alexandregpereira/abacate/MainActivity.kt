package br.alexandregpereira.abacate

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.alexandregpereira.abacate.ui.component.ActionHeader
import br.alexandregpereira.abacate.ui.theme.AbacateTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Window()
        }
    }
}

@Composable
fun Window() = AbacateTheme {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        ActionHeader(text = "Anything asdsa")
    }
}

@Preview
@Composable
fun DefaultPreview() {
    Window()
}