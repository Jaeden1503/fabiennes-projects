package com.example.safehome.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safehome.data.DataSource
import com.example.safehome.model.ImageSliderHome
import com.example.safehome.ui.theme.SafeHomeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import com.example.safehome.R
import com.example.safehome.model.Review

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navigateToHouseDetails: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val state = rememberPagerState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally) {

        //Logo
        Image(painter = painterResource(id = R.drawable.logo_without_text),
            contentDescription = R.drawable.logo_without_text.toString(),
            modifier = Modifier
                .size(90.dp, 90.dp)
                .padding(top = 10.dp, bottom = 10.dp)
        )

        //Image slider
        SliderHomeView(state, DataSource.imageHomeList)

        Spacer(modifier = Modifier.padding(4.dp))
        DotsIndicator(
            totalDots = DataSource.imageDetailsList.size,
            selectedIndex = state.currentPage
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
            horizontalAlignment = Alignment.Start) {

            //Recommendation section
            RecommendationSection(navigateToHouseDetails)

            Spacer(modifier = Modifier.height(20.dp))

            //Reviews
            Text(
                text = "Reviews",
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                fontSize = 18.sp,
                color = colorResource(id = R.color.dark_blue),
                modifier = Modifier.padding(bottom = 15.dp),
                textAlign = TextAlign.Start
            )
            ReviewsList()
        }
    }


    LaunchedEffect(key1 = state.currentPage) {
        delay(3500)
        var newPosition = state.currentPage + 1
        if (newPosition > 3 - 1) newPosition = 0
        // scrolling to the new position.
        state.animateScrollToPage(newPosition)
    }
}


@Composable
fun RecommendationSection(navigateToHouseDetails: () -> Unit){
    Text(
        text = "Recommendation",
        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
        fontSize = 18.sp,
        color = colorResource(id = R.color.dark_blue),
        modifier = Modifier
            .padding(bottom = 15.dp)
            .wrapContentWidth(align = Alignment.Start),
        textAlign = TextAlign.Start
    )

    Row(modifier = Modifier.fillMaxWidth().wrapContentWidth()) {
        Image(painter = painterResource(id = R.drawable.img1), contentScale = ContentScale.Crop,
            contentDescription = "", modifier = Modifier
                .size(90.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .border(2.dp, Color.DarkGray, RoundedCornerShape(10.dp))
                .clickable { navigateToHouseDetails() })

        Image(painter = painterResource(id = R.drawable.img2), contentScale = ContentScale.Crop,
            contentDescription = "", modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .size(90.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .border(2.dp, Color.DarkGray, RoundedCornerShape(10.dp))
                .clickable { navigateToHouseDetails() })

        Image(painter = painterResource(id = R.drawable.img3), contentScale = ContentScale.Crop,
            contentDescription = "", modifier = Modifier
                .size(90.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .border(2.dp, Color.DarkGray, RoundedCornerShape(10.dp))
                .clickable { navigateToHouseDetails() })
    }
}


/////// Code for horizontal slider ///////
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderHomeView(state: PagerState, imageHome: List<ImageSliderHome>) {

    HorizontalPager(
        state = state,
        count = DataSource.imageDetailsList.size, modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) { page ->

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.Center) {

                Image(
                    painter = painterResource(id = imageHome[page].imageRes), contentDescription = "",
                    Modifier
                        .padding(start = 25.dp, end = 25.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxSize(), contentScale = ContentScale.Crop
                )
                Text(
                    text = imageHome[page].name,
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(start = 25.dp, end = 25.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.LightGray.copy(alpha = 0.60F))
                        .wrapContentHeight(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    color = colorResource(R.color.dark_blue)
                )
            }
        }
    }
}


@Composable
fun ReviewsList() {
    Column {
        DataSource.reviewList.forEach { item ->
            ReviewCard(review = item)
        }
    }
}

@Composable
fun ReviewCard(review: Review) {

    val context = LocalContext.current

    Card(modifier = Modifier
        .padding(bottom = 15.dp)
        .widthIn(0.dp, 340.dp)
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()) {

        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            //image profile
            Image(painter = painterResource(id = review.imageRes), contentDescription = review.imageRes.toString(),
                contentScale = ContentScale.Crop, modifier = Modifier
                    .padding(end = 10.dp)
                    .size(40.dp)
                    .clip(shape = CircleShape)
            )

            Column {
                //name
                Text(text = context.getString(review.name), fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp, color = colorResource(R.color.dark_blue))

                //description
                Text(text = context.getString(review.description), fontFamily = FontFamily.SansSerif,
                    fontSize = 13.sp, color = Color.DarkGray)
            }
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
            HomeScreen(
                navigateToHouseDetails = {}
            )
        }
    }
}