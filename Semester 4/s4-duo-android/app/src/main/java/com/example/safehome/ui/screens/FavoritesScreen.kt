package com.example.safehome.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.safehome.R
import com.example.safehome.data.DataSource
import com.example.safehome.model.Home
import com.example.safehome.ui.theme.SafeHomeTheme

@Composable
fun FavoritesScreen(
    navigateToHouseDetails: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = "Favorites", fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            color = colorResource(R.color.dark_blue),
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))

        FavoriteHomesList(navigateToHouseDetails = navigateToHouseDetails)
    }
}

@Composable
fun FavoriteHomesList(modifier: Modifier = Modifier, navigateToHouseDetails: () -> Unit) {
    LazyColumn {
        items(DataSource.favoriteHomes) {
            favoriteHome -> FavoritesCard(
            favoriteHome = favoriteHome,
            navigateToHouseDetails = navigateToHouseDetails)
        }
    }
}

@Composable
fun FavoritesCard(favoriteHome: Home, modifier: Modifier = Modifier, navigateToHouseDetails: () -> Unit) {
    Card(modifier = Modifier
        .padding(start = 25.dp, top = 10.dp, end = 25.dp, bottom = 10.dp)
        .widthIn(0.dp, 340.dp)
        .clip(RoundedCornerShape(10.dp))
        .clickable { navigateToHouseDetails() }, elevation = 4.dp) {
        Column {
            Box(modifier = Modifier) {

                Image(
                    painter = painterResource(favoriteHome.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(184.dp),
                    contentScale = ContentScale.Crop
                )
                displayToggleButton(true, modifier = Modifier.padding(10.dp))
            }

            Row {
                Text(
                    text = LocalContext.current.getString(favoriteHome.address),
                    modifier = Modifier.padding(start = 16.dp, top = 12.dp, end = 6.dp, bottom = 6.dp),
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    color = colorResource(R.color.dark_blue)
                )
                Text(
                    text = LocalContext.current.getString(favoriteHome.location),
                    modifier = Modifier.padding(start = 6.dp, top = 12.dp, end = 16.dp, bottom = 6.dp),
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    color = colorResource(R.color.dark_blue)
                )
            }
            Text(
                text = "€ " + favoriteHome.price.toString(),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                color = colorResource(R.color.dark_blue)
            )
        }
    }
}

// on below line we are creating a function
// to get current date and time.
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun displayToggleButton(isLiked: Boolean, modifier: Modifier) {

    val checkedState = remember { mutableStateOf(isLiked) }

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top
    ) {
        // on below line we are creating icon toggle button.
        IconToggleButton(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = !checkedState.value
            },
            modifier = modifier
        ) {
            // on below line we are creating a
            // variable for our transition
            val transition = updateTransition(checkedState.value, label = "transition")

            // on below line we are creating a variable for
            // color of our icon
            val tint by transition.animateColor(label = "iconColor") { isChecked ->
                if (isChecked) Color.Red else Color.Black
            }

            // om below line we are specifying transition
            val size by transition.animateDp(
                transitionSpec = {
                    // on below line we are specifying transition
                    if (false isTransitioningTo true) {
                        // on below line we are specifying key frames
                        keyframes {
                            // on below line we are specifying animation duration
                            durationMillis = 250
                            // on below line we are specifying animations.
                            30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                            35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                            40.dp at 75 // ms
                            35.dp at 150 // ms
                        }
                    } else {
                        spring(stiffness = Spring.StiffnessVeryLow)
                    }
                },
                label = "Size"
            ) { 25.dp }

            // on below line we are creating icon for our toggle button.
            Icon(
                // on below line we are specifying icon for our image vector.
                imageVector = if (checkedState.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Icon",
                // on below line we are specifying
                // tint for our icon.
                tint = tint,
                // on below line we are specifying
                // size for our icon.
                modifier = Modifier.size(size)
            )
        }
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
            FavoritesScreen(
                navigateToHouseDetails = {}
            )
        }
    }
}