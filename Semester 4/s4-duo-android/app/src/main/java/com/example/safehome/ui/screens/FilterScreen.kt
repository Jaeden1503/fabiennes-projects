package com.example.safehome.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safehome.R
import com.example.safehome.data.DataSource
import com.example.safehome.ui.theme.SafeHomeTheme

@Composable
fun FilterScreen(popBackStack: () -> Unit, navigateToResults: () -> Unit) {
    val scrollState = rememberScrollState()

    //cross button top right corner
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(25.dp)
        .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.End) {

        Icon(
            imageVector = Icons.Default.Close, contentDescription = "close",
            modifier = Modifier.clickable { popBackStack() },
            tint = colorResource(id = R.color.dark_blue)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Filter",
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                fontSize = 22.sp,
                color = colorResource(id = R.color.dark_blue),
                modifier = Modifier.padding(top = 15.dp, bottom = 30.dp),
            )

            //All the dropdowns
            ExpandableCard("Location", DataSource.locationRadioButtons)
            ExpandableCard("Price", DataSource.priceRadioButtons)
            ExpandableCard("Type", DataSource.typeRadioButtons)
            ExpandableCard("Number of people", DataSource.numberOfXRadioButton)
            ExpandableCard("Number of rooms", DataSource.numberOfXRadioButton)
            ExpandableCard("Surface", DataSource.surfaceRadioButton)

            //button "done"
            Button(onClick = { navigateToResults() },
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.dark_blue))) {
                Text(text = "Done",
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    color = Color.White
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(titleText: String, priceRadioButtons: List<String>) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = RoundedCornerShape(10.dp),
        onClick = { expandedState = !expandedState }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 2.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    modifier = Modifier.weight(6f),
                    text = titleText,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = { expandedState = !expandedState }) {

                    Icon(imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow",
                        tint = colorResource(id = R.color.extra_dark_green),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            if (expandedState) {
                DropdownRadioButtons(priceRadioButtons)
            }
        }
    }
}


@Composable
fun DropdownRadioButtons(listRadioButtons: List<String>) {
    val selectedValue = remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    Column(Modifier.padding(horizontal = 8.dp, vertical = 2.dp)) {

        listRadioButtons.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = isSelectedItem(item),
                        onClick = { onChangeState(item) },
                        role = Role.RadioButton
                    )
                    .padding(6.dp)
            ) {
                RadioButton(
                    selected = isSelectedItem(item),
                    onClick = null,
                    colors = RadioButtonDefaults.colors(selectedColor = colorResource(id = R.color.extra_dark_green))
                )
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                )
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
            FilterScreen(
                popBackStack = {},
                navigateToResults = {}
            )
        }
    }
}