package com.example.safehome.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.safehome.ui.theme.SafeHomeTheme
import com.example.safehome.R

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit
) {

    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = painterResource(id = R.drawable.logo_without_text),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .padding(top = 40.dp))

        Spacer(modifier = Modifier.height(40.dp))
        
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = (Icons.Default.AccountCircle).toString(),
            tint = colorResource(id = R.color.dark_blue),
            modifier = Modifier.size(200.dp, 200.dp)
        )
        
        Spacer(modifier = Modifier.height(30.dp))

        //Login button, navigates to the home page
        Button(onClick = { navigateToHome() },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
            border = BorderStroke(1.dp, colorResource(id = R.color.dark_blue))
        ) {
            Text(text = "Log In", color = colorResource(R.color.dark_blue),
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        //Sign up button
        Button(onClick = { navigateToHome() },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
            border = BorderStroke(1.dp, colorResource(id = R.color.dark_blue))
        ) {
            Text(text = "Sign up", color = colorResource(R.color.dark_blue),
            fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )
        }
        
        Text(text = "use as a guest", modifier = Modifier
            .padding(top = 15.dp)
            .clickable { navigateToHome() },
            color = Color.DarkGray, fontFamily = FontFamily(Font(R.font.montserrat_bold)
            )
        )
    }
}


@Preview (showBackground = true)
@Composable
private fun DefaultPreview() {
    SafeHomeTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LoginScreen(
                navigateToHome = {}
            )
        }
    }
}