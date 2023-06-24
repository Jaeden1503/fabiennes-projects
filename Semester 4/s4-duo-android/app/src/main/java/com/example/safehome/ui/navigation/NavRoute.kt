package com.example.safehome.ui.navigation

sealed class NavRoute(val path: String) {

    //all the different pages
    object Login: NavRoute("login")

    object Home: NavRoute("home")

    object Profile: NavRoute("profile")

    object Search: NavRoute("search")

    object Favorite: NavRoute("favorite")

    object HouseDetails: NavRoute("houseDetails")

    object Filter: NavRoute("filter")

    object Results: NavRoute("results")

    object Questions: NavRoute("questions")
}
