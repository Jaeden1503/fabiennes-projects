package com.example.safehome.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.safehome.R


@Composable
fun BottomBarNav(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute == null || currentRoute == NavRoute.Login.path) {
        return
    }

    BottomNavigation(backgroundColor = Color.White, contentColor = colorResource(id = R.color.dark_blue)) {

        //navigate to favorites
        val favoriteSelected =  currentRoute == NavRoute.Favorite.path
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = NavRoute.Favorite.path
                )
            },
            selected = favoriteSelected,
            onClick = {
                if(!favoriteSelected) {
                    navController.navigate(NavRoute.Favorite.path)
                }
            },
            label = { Text(NavRoute.Favorite.path) }
        )

        //navigate to home
        val homeSelected = currentRoute == NavRoute.Home.path
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = NavRoute.Home.path
                )
            },
            selected = homeSelected,
            onClick = {
                if(!homeSelected) {
                    navController.navigate(NavRoute.Home.path) {
                        popUpTo(NavRoute.Home.path) { inclusive = true }
                    }
                }
            },
            label = {Text(NavRoute.Home.path)}
        )

        //navigate to search
        val searchSelected =  currentRoute == NavRoute.Search.path
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = NavRoute.Search.path
                )
            },
            selected = searchSelected,
            onClick = {
                if(!searchSelected) {
                    navController.navigate(NavRoute.Search.path)
                }
            },
            label = { Text(NavRoute.Search.path) }
        )

        //navigate to profile
        val profileSelected =  currentRoute == NavRoute.Profile.path
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = NavRoute.Profile.path
                )
            },
            selected = profileSelected,
            onClick = {
                if(!profileSelected) {
                    navController.navigate(NavRoute.Profile.path)
                }
            },
            label = { Text(NavRoute.Profile.path) }
        )
    }
}