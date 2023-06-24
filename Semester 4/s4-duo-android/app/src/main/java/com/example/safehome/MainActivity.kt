package com.example.safehome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.safehome.ui.navigation.BottomBarNav
import com.example.safehome.ui.navigation.NavGraph
import com.example.safehome.ui.theme.SafeHomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SafeHomeTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreen() {
    SafeHomeTheme {
        val navController = rememberNavController()

        Scaffold (topBar = { TopAppBar(backgroundColor = Color.White,
            title = {
            Text(text = "SafeHome",modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.dark_blue))
            })
        },
        bottomBar = { BottomBarNav(navController = navController) }

        ) { paddingValues ->
            NavGraph(
                modifier = Modifier.padding(
                    bottom = paddingValues.calculateBottomPadding()),
                navController = navController
            )
        }
    }
}