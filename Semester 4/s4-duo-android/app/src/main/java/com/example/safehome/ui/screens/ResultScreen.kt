package com.example.safehome.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.safehome.R
import com.example.safehome.data.DataSource
import com.example.safehome.ui.theme.SafeHomeTheme

@Composable
fun ResultScreen(
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

        //results
        //HomesList(navigateToHouseDetails, DataSource.recommendedHomes, "Recommendation")
        HomesList(navigateToHouseDetails, DataSource.resultHomes, "Results")
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
            ResultScreen(
                navigateToFilter = {},
                navigateToHouseDetails = {}
            )
        }
    }
}