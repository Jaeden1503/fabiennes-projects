package com.example.safehome.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.safehome.LoginActivity
import com.example.safehome.R
import com.example.safehome.model.ComposeFileProvider
import com.example.safehome.ui.theme.SafeHomeTheme

@Composable
fun ProfileScreen() {

    val context = LocalContext.current

    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            hasImage = success
        }
    )

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        //Logo
        Image(
            painter = painterResource(id = R.drawable.logo_without_text),
            contentDescription = R.drawable.logo_without_text.toString(),
            modifier = Modifier
                .size(90.dp, 90.dp)
                .padding(top = 10.dp, bottom = 10.dp)
        )

        //Change profile picture section
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (hasImage && imageUri != null) {

                AsyncImage(
                    model = imageUri,
                    modifier = Modifier
                        .size(120.dp, 120.dp)
                        .clip(shape = CircleShape),
                    contentDescription = "Selected image",
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(0.dp)
                        .size(130.dp, 130.dp),
                        tint = colorResource(id = R.color.dark_blue)
                )
            }
            
            
            Row() {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_camera_alt_24), 
                    contentDescription = "take a photo",
                    tint = colorResource(id = R.color.extra_dark_green),
                    modifier = Modifier
                        .padding(top = 20.dp, end = 10.dp)
                        .size(30.dp)
                        .clickable {
                            val uri = ComposeFileProvider.getImageUri(context)
                            imageUri = uri
                            cameraLauncher.launch(uri)
                        }

                    )

                Icon(painter = painterResource(id = R.drawable.ic_baseline_insert_photo_24),
                    contentDescription = "take a photo",
                    tint = colorResource(id = R.color.extra_dark_green),
                    modifier = Modifier
                        .padding(start = 10.dp, top = 20.dp)
                        .size(30.dp)
                        .clickable { imagePicker.launch("image/*") }
                )
            }
        }
        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Your profile",
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            fontSize = 20.sp,
            color = colorResource(id = R.color.dark_blue),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = "Your group profile",
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            fontSize = 20.sp,
            color = colorResource(id = R.color.dark_blue),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = "FAQ",
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            fontSize = 20.sp,
            color = colorResource(id = R.color.dark_blue),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = "Privacy",
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            fontSize = 20.sp,
            color = colorResource(id = R.color.dark_blue),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = "Contact us",
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            fontSize = 20.sp,
            color = colorResource(id = R.color.dark_blue),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Log out",
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            fontSize = 20.sp,
            color = Color.Red,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .clickable { context.startActivity(Intent(context, LoginActivity::class.java)) }
        )
    }
}


@Preview
@Composable
private fun DefaultPreview() {
    SafeHomeTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            ProfileScreen()
        }
    }
}