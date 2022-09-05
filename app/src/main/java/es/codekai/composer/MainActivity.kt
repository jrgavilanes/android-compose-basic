package es.codekai.composer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import es.codekai.composer.screens.LoginScreen
import es.codekai.composer.ui.theme.ComposerTheme

class MainActivity : ComponentActivity() {
    override fun onBackPressed() {
//        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposerTheme {
                LoginScreen(MainViewModel())
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


