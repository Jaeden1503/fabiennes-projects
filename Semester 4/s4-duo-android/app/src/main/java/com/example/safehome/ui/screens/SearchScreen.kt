package com.example.safehome.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safehome.R
import com.example.safehome.data.DataSource
import com.example.safehome.model.Home
import com.example.safehome.ui.theme.SafeHomeTheme

@Composable
fun SearchScreen(
    navigateToFilter: () -> Unit, navigateToHouseDetails: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        //Logo
        Image(
            painter = painterResource(id = R.drawable.logo_without_text),
            contentDescription = R.drawable.logo_without_text.toString(),
            modifier = Modifier
                .size(90.dp, 90.dp)
                .padding(top = 10.dp)
        )

        //search bar + filter icon (load to filter page)
        SearchbarAndFilter(navigateToFilter)

        Spacer(modifier = Modifier.height(15.dp))

        //(recommended) results
        HomesList(navigateToHouseDetails, DataSource.recommendedHomes, "Recommendation")
        //HomesList(navigateToHouseDetails, DataSource.resultHomes, "Results")
    }
}

@Composable
fun SearchbarAndFilter(navigateToFilter: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically){
        var text by remember { mutableStateOf(TextFieldValue("")) }
        val focusManager = LocalFocusManager.current

        OutlinedTextField(
            value = text,
            onValueChange = { text = it},
            label = {
                Text(text = "Search")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = "search here")
            },

            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            shape = RoundedCornerShape(20.dp),
            singleLine = true
        )
        
        //filter icon
        Icon(painter = painterResource(id = R.drawable.ic_baseline_filter_alt_24),
            contentDescription = "filter",
            tint = colorResource(id = R.color.dark_green),
            modifier = Modifier
                .padding(start = 10.dp)
                .size(35.dp, 35.dp)
                .clickable { navigateToFilter() })
    }
}


@Composable
fun HomesList(navigateToHouseDetails: () -> Unit, homesList: List<Home>, title: String) {
    Text(
        text = title,
        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
        fontSize = 18.sp,
        color = colorResource(id = R.color.dark_blue),
        modifier = Modifier
            .padding(bottom = 15.dp)
            .wrapContentWidth(align = Alignment.Start),
        textAlign = TextAlign.Start
    )

    LazyColumn {
        items(homesList) {
                homes -> HomeCard(
            home = homes,
            navigateToHouseDetails = navigateToHouseDetails)
        }
    }
}


@Composable
fun HomeCard(home: Home, modifier: Modifier = Modifier, navigateToHouseDetails: () -> Unit) {
    Card(modifier = Modifier
        .padding(start = 25.dp, top = 10.dp, end = 25.dp, bottom = 10.dp)
        .widthIn(0.dp, 340.dp)
        .clip(RoundedCornerShape(10.dp))
        .clickable { navigateToHouseDetails() }, elevation = 4.dp) {
        Column {
            Box(modifier = Modifier) {

                Image(
                    painter = painterResource(home.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(184.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Row {
                Text(
                    text = LocalContext.current.getString(home.address),
                    modifier = Modifier.padding(start = 16.dp, top = 12.dp, end = 6.dp, bottom = 6.dp),
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    color = colorResource(R.color.dark_blue)
                )
                Text(
                    text = LocalContext.current.getString(home.location),
                    modifier = Modifier.padding(start = 6.dp, top = 12.dp, end = 16.dp, bottom = 6.dp),
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    color = colorResource(R.color.dark_blue)
                )
            }
            Text(
                text = "€ " + home.price.toString(),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                color = colorResource(R.color.dark_blue)
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
            SearchScreen(
                navigateToFilter = {},
                navigateToHouseDetails = {}
            )
        }
    }
}