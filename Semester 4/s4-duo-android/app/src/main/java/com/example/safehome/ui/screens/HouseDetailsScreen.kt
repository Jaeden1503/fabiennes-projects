package com.example.safehome.ui.screens

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.safehome.R
import com.example.safehome.data.DataSource
import com.example.safehome.model.ImageSliderDetails
import com.example.safehome.ui.theme.SafeHomeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HouseDetailsScreen( popBackStack: () -> Unit) {

    val scrollState = rememberScrollState()
    val state = rememberPagerState()
    val informationText = "The redevelopment of one of the most characteristic buildings of Eindhoven, " +
            "the Bunkertoren, is currently taking place in the city center. The completion will take " +
            "place mid-November. \n \n" +
            "The offered object will equipped with all amenities and has a fan-tas-tic view! " +
            "The object will be delivered to a very high standard. \n \n" +
            "The apartment is located on the 7th floor \n \n" +
            "This floor is accessible via the indoor elevator. Which can be reached via the " +
            "general entrance hall. This entrance hall has an intercom system and of " +
            "course a personal mailbox."

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.Start) {

        Row(modifier = Modifier.padding(top = 10.dp)) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = Icons.Default.ArrowBack.toString(),
                modifier = Modifier
                    .padding(start = 8.dp, top = 5.dp)
                    .clickable { popBackStack() },
                tint = colorResource(id = R.color.dark_blue)
            )
            Text(text = "House details",
                fontSize = 25.sp, textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 35.dp),
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                color = colorResource(R.color.dark_blue)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        //image slider
        Column {
            SliderView(state, DataSource.imageDetailsList)

            Spacer(modifier = Modifier.padding(4.dp))
            DotsIndicator(
                totalDots = DataSource.imageDetailsList.size,
                selectedIndex = state.currentPage
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        //the rest of the page, including facilities, more information, characteristics
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
            horizontalAlignment = Alignment.Start) {

            Row {
                Text(
                    text = "Stratumseind 15, Eindhoven",
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.dark_blue),
                    modifier = Modifier.padding(bottom = 25.dp)
                )
                displayToggleButton(isLiked = false, modifier = Modifier.padding(0.dp))
            }

            //facilities section including icons
            FacilitiesSection()

            //more information section
            Text(
                text = "More information",
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                fontSize = 18.sp,
                color = colorResource(id = R.color.dark_blue),
                modifier = Modifier.padding(top = 30.dp, bottom = 15.dp)
            )
            //white text box with expandable description
            ExpandingText(text = informationText)

            //characteristics
            CharacteristicsSection()
        }

        //apply for one button
        NotificationApp(textTitle = "Applied to a new house",
            textContent = "You applied for one, your application will be viewed soon",
            buttonText = "Apply for one")

        //apply as group button
        NotificationApp(textTitle = "Applied to a new house",
            textContent = "You applied as a group, your application will be viewed soon",
            buttonText = "Apply as group")
    }
}

@Composable
fun FacilitiesSection() {
    Text(
        text = "Facilities",
        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
        fontSize = 18.sp,
        color = colorResource(id = R.color.dark_blue),
        modifier = Modifier.padding(bottom = 15.dp)
    )
    Row (modifier = Modifier.padding(bottom = 20.dp)) {
        //Room size icon
        Icon(painter = painterResource(id = R.drawable.ic_baseline_crop_free_24),
            contentDescription = R.drawable.ic_baseline_crop_free_24.toString(),
            tint = colorResource(id = R.color.dark_green),
            modifier = Modifier.size(35.dp,35.dp)
        )
        Text(text = "57m2",
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            modifier = Modifier.padding(start = 10.dp, top = 9.dp, end = 40.dp)
        )
        //Rooms icon
        Icon(painter = painterResource(id = R.drawable.ic_outline_door_front_24),
            contentDescription = R.drawable.ic_baseline_crop_free_24.toString(),
            tint = colorResource(id = R.color.dark_green),
            modifier = Modifier.size(35.dp,35.dp)
        )
        Text(text = "2 Rooms",
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            modifier = Modifier.padding(start = 10.dp, top = 9.dp, end = 30.dp)
        )
    }
    Row {
        //People icon
        Icon(imageVector = Icons.Outlined.Person,
            contentDescription = Icons.Default.Person.toString(),
            tint = colorResource(id = R.color.dark_green),
            modifier = Modifier.size(35.dp,35.dp)
        )
        Text(text = "2 People",
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            modifier = Modifier.padding(start = 10.dp, top = 9.dp, end = 15.dp)
        )
        //Euro icon
        Icon(painter = painterResource(id = R.drawable.ic_baseline_euro_symbol_24),
            contentDescription = R.drawable.ic_baseline_euro_symbol_24.toString(),
            tint = colorResource(id = R.color.dark_green),
            modifier = Modifier.size(35.dp,35.dp)
        )
        Text(text = "350,-",
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            modifier = Modifier.padding(start = 10.dp, top = 9.dp, end = 15.dp)
        )
    }
}


/////// Functions for notification ///////
@Composable
fun NotificationApp(textTitle: String, textContent: String, buttonText: String) {
    val context = LocalContext.current
    val channelId = "MyTestChannel"
    val notificationId = 0

    LaunchedEffect(Unit) {
        createNotificationChannel(channelId = channelId, context = context)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {

        Button(onClick = { showSimpleNotification(
            context, channelId, notificationId,
            textTitle = textTitle,
            textContent = textContent)},
            modifier = Modifier.padding(top = 15.dp, bottom = 10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.dark_blue))) {
                Text(text = buttonText,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    color = Color.White
                )
        }
    }
}


private fun createNotificationChannel(channelId: String, context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "MyTestChannel"
        val descriptionText = "My important test channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

private fun showSimpleNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(priority)

    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }
}

/////// Code for expanding text ///////
private const val MINIMIZED_MAX_LINES = 3

@Composable
fun ExpandingText(text: String) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    var finalText by remember { mutableStateOf(text) }

    val textLayoutResult = textLayoutResultState.value
    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded -> {
                finalText = "$text Show Less"
            }
            !isExpanded && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(MINIMIZED_MAX_LINES - 1)
                val showMoreString = "... Show More"
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile { it == ' ' || it == '.' }

                finalText = "$adjustedText$showMoreString"

                isClickable = true
            }
        }
    }

    Text(
        text = finalText,
        maxLines = if (isExpanded) Int.MAX_VALUE else MINIMIZED_MAX_LINES,
        onTextLayout = { textLayoutResultState.value = it },
        fontFamily = FontFamily.SansSerif,
        fontSize = 15.sp,
        lineHeight = 18.sp,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = Color.White)
            .padding(10.dp)
            .clickable(enabled = isClickable) { isExpanded = !isExpanded }
            .animateContentSize(),
    )
}


/////// Code for horizontal slider ///////
@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderView(state: PagerState, imageDetail: List<ImageSliderDetails>) {

    HorizontalPager(
        state = state,
        count = DataSource.imageDetailsList.size, modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) { page ->

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {

                Image(
                    painter = painterResource(id = imageDetail[page].imageRes), contentDescription = "",
                    Modifier
                        .padding(start = 25.dp, end = 25.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxSize(), contentScale = ContentScale.Crop
                )
            }
        }
    }
}


/////// Code for indicator dots ///////
@Composable
fun DotsIndicator(totalDots: Int, selectedIndex: Int) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.Center
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.DarkGray)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}


@Composable
fun CharacteristicsSection() {
    Text(
        text = "Characteristics",
        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
        fontSize = 18.sp,
        color = colorResource(id = R.color.dark_blue),
        modifier = Modifier.padding(top = 30.dp, bottom = 15.dp)
    )
    Box(modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .background(Color.White)
        .fillMaxWidth()) {

        Column {
            Row { Text(text = "Location:", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
                Text(text = "Stratumseind 15", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 105.dp, top = 10.dp))
            }
            Row { Text(text = "Zip code:", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
                Text(text = "5611 EN Eindhoven", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 105.dp, top = 10.dp))
            }
            Row { Text(text = "Price:", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
                Text(text = "€ 350", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 128.dp, top = 10.dp))
            }
            Row { Text(text = "Offered since:", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
                Text(text = "4 weeks", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 73.dp, top = 10.dp))
            }
            Row { Text(text = "Status:", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
                Text(text = "Available", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 120.dp, top = 10.dp))
            }
            Row { Text(text = "Construction year:", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
                Text(text = "1938", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 45.dp, top = 10.dp))
            }
            Row { Text(text = "Living:", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
                Text(text = "52m2", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 125.dp, top = 10.dp))
            }
            Row { Text(text = "Number of rooms:", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
                Text(text = "2", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 48.dp, top = 10.dp))
            }
            Row { Text(text = "Number of bathrooms:", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
                Text(text = "1", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 19.dp, top = 10.dp))
            }
            Row { Text(text = "Bathroom amenities:", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
                Text(text = "Shower and toilet", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 30.dp, top = 10.dp))
            }
            Row { Text(text = "Number of floors:", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
                Text(text = "2", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 54.dp, top = 10.dp))
            }
            Row { Text(text = "Energy label:", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 10.dp, top = 10.dp))
                Text(text = "C", fontFamily = FontFamily.SansSerif, fontSize = 15.sp, modifier = Modifier.padding(start = 85.dp, top = 10.dp, bottom = 10.dp))
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
            HouseDetailsScreen(
                popBackStack = {}
            )
        }
    }
}