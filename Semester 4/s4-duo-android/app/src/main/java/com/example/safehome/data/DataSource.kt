package com.example.safehome.data

import com.example.safehome.R
import com.example.safehome.model.*

object DataSource {
    ///// Favorite page /////
    val favoriteHomes = listOf(
        Home(R.string.address1, R.drawable.img1, 350, R.string.location1),
        Home(R.string.address2, R.drawable.img2, 390, R.string.location3),
        Home(R.string.address3, R.drawable.img3, 900, R.string.location2),
        Home(R.string.address4, R.drawable.img4, 225, R.string.location1),
        Home(R.string.address5, R.drawable.img5, 800, R.string.location1),
        Home(R.string.address6, R.drawable.img6, 375, R.string.location2),
        Home(R.string.address7, R.drawable.img7, 650, R.string.location1),
    )

    ///// Details page /////
    val imageDetailsList = listOf(
        ImageSliderDetails(R.drawable.img1),
        ImageSliderDetails(R.drawable.img2),
        ImageSliderDetails(R.drawable.img3)
    )

    ///// Home page /////
    val imageHomeList = listOf(
        ImageSliderHome("Find a suitable place for you!",R.drawable.img1),
        ImageSliderHome("Start by searching",R.drawable.img2),
        ImageSliderHome("Or click on a recommendation!",R.drawable.img3)
    )

    val reviewList = listOf(
        Review(R.string.name, R.drawable.person2, R.string.description),
        Review(R.string.name2, R.drawable.person3, R.string.description2),
        Review(R.string.name3, R.drawable.person4, R.string.description3),
        Review(R.string.name4, R.drawable.person1, R.string.description4),
    )

    ///// different types of radio buttons for the filter screen /////
    val locationRadioButtons = listOf(
        "Eindhoven", "Veldhoven", "Amsterdam"
    )

    val priceRadioButtons = listOf(
        "< €500", "< €1000", "< €1500", "< €2000", "< €2500"
    )

    val typeRadioButtons = listOf(
        "House", "Apartment"
    )

    val numberOfXRadioButton = listOf(
        "1", "2", "3", "4", "5"
    )

    val surfaceRadioButton = listOf(
        "50m2 or more", "75m2 or more", "100m2 or more", "150m2 or more"
    )

    ///// Recommended houses on the search page /////
    val recommendedHomes = listOf(
        Home(R.string.address8, R.drawable.img8, 725, R.string.location2),
        Home(R.string.address9, R.drawable.img9, 150, R.string.location3),
        Home(R.string.address10, R.drawable.img10, 850, R.string.location3)
    )

    val resultHomes = listOf(
        Home(R.string.address1, R.drawable.img1, 350, R.string.location1),
        Home(R.string.address2, R.drawable.img2, 390, R.string.location1),
        Home(R.string.address4, R.drawable.img4, 225, R.string.location1),
        Home(R.string.address5, R.drawable.img5, 800, R.string.location1),
        Home(R.string.address6, R.drawable.img6, 375, R.string.location1),
        Home(R.string.address7, R.drawable.img7, 650, R.string.location1),
    )
}
