package com.example.safehome.ui.screens

import android.app.DatePickerDialog
import android.net.Uri
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import coil.compose.AsyncImage
import com.example.safehome.R
import com.example.safehome.ui.theme.SafeHomeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import java.util.*


@OptIn(ExperimentalPagerApi::class)
@Composable
fun QuestionsScreen(navigateToHome: () -> Unit){
    val state = rememberPagerState()

    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        //Logo
        Image(
            painter = painterResource(id = R.drawable.logo_without_text),
            contentDescription = R.drawable.logo_without_text.toString(),
            modifier = Modifier
                .size(90.dp, 90.dp)
                .padding(top = 10.dp, bottom = 10.dp)
        )

        Text(
            text = "Before we start answer \n these questions",
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            fontSize = 20.sp,
            color = colorResource(id = R.color.dark_blue),
            modifier = Modifier.padding(start = 30.dp, end = 30.dp, bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        QuestionsSliderView(state = state)

        Spacer(modifier = Modifier.height(8.dp))
        DotsIndicator(totalDots = 4, selectedIndex = state.currentPage)

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { navigateToHome() },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
            border = BorderStroke(1.dp, colorResource(id = R.color.dark_blue))
        ) {
            Text(text = "Done", color = colorResource(R.color.dark_blue),
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )
        }
    }
}

/////// Code for horizontal slider ///////
@OptIn(ExperimentalPagerApi::class)
@Composable
fun QuestionsSliderView(state: PagerState) {

    HorizontalPager(
        state = state, count = 4,
        modifier = Modifier
            .height(330.dp)
            .fillMaxWidth()
    ) { page ->

        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)) {
                
                if (page == 0){
                    // load in questions for page 1 
                    QuestionsPageOne()
                }
                if (page == 1){
                    // load in questions for page 2 
                    QuestionsPageTwo()
                }
                if (page == 2){
                    // load in questions for page 3
                    QuestionsPageThree()
                }
                if (page == 3){
                    // load in questions for page 4
                    QuestionsPageFour()
                }
            }
        }
    }
}

@Composable
fun QuestionsPageOne() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var listGender = listOf( "Female", "Male", "Other")
    val focusManager = LocalFocusManager.current

    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int
    val calendar = Calendar.getInstance()

    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()
    val date = remember { mutableStateOf("") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/${month+1}/$year"
        }, year, month, day
    )

    Column {
        Text(text = "Name:", modifier = Modifier.padding(bottom = 4.dp))
        TextField(value = text, onValueChange = {text = it},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() })
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Gender:")
        DropdownRadioButtons(listRadioButtons = listGender)
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Date of birth:")
        Row(verticalAlignment = Alignment.Bottom) {
            Icon(painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
                contentDescription = "choose date",
                tint = colorResource(id = R.color.dark_blue),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(30.dp)
                    .clickable { datePickerDialog.show() }
            )
            Text(text = "${date.value}", modifier = Modifier.padding(start = 8.dp),
                fontFamily = FontFamily.SansSerif)
        }
    }
}

@Composable
fun QuestionsPageTwo() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var textBudget by remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current

    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column {
        Text(text = "Describe yourself:", modifier = Modifier.padding(bottom = 4.dp))
        TextField(value = text, onValueChange = {text = it} , maxLines = 3,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() })
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Upload your photo:")

        Row(modifier = Modifier.padding(top = 10.dp)) {
            Icon(painter = painterResource(id = R.drawable.ic_baseline_insert_photo_24),
                contentDescription = "choose image",
                tint = colorResource(id = R.color.dark_blue),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(30.dp)
                    .clickable { imagePicker.launch("image/*") }
            )
            if (hasImage && imageUri != null) {
                AsyncImage(
                    model = imageUri,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(shape = RectangleShape),
                    contentDescription = "Selected image",
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(50.dp)
                        .clip(RectangleShape)
                        .background(colorResource(id = R.color.light_green))
                        , contentAlignment = Alignment.Center

                )
                { Text(text = " Insert \n Image", fontSize = 15.sp) }
            }
        }


        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Your budget:", modifier = Modifier.padding(bottom = 4.dp))
        TextField(value = textBudget, onValueChange = {textBudget = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() })
        )
    }
}

@Composable
fun QuestionsPageThree() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current
    var listLivingPreference = listOf( "Alone", "With a group")
    var listYesNo = listOf( "Yes", "No")

    Column {
        Text(text = "Current residence:", modifier = Modifier.padding(bottom = 4.dp))
        TextField(value = text, onValueChange = {text = it}, maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() })
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "How do you prefer to live:")
        DropdownRadioButtons(listRadioButtons = listLivingPreference)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Do you smoke:")
        DropdownRadioButtons(listRadioButtons = listYesNo)
    }
}

@Composable
fun QuestionsPageFour() {
    var listStatus = listOf( "Student", "Employee", "Looking for a job")
    var listYesNo = listOf( "Yes", "No")


    Column {
        Text(text = "What is your status:")
        DropdownRadioButtons(listRadioButtons = listStatus)

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Do you have pets:")
        DropdownRadioButtons(listRadioButtons = listYesNo)
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
            QuestionsScreen(
                navigateToHome = {}
            )
        }
    }
}



