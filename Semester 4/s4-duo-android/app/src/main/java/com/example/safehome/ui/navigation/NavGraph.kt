package com.example.safehome.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.safehome.ui.screens.*

@Composable
fun NavGraph(modifier: Modifier = Modifier, navController: NavHostController) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoute.Questions.path //default screen when startup
    ) {
        addLoginScreen(navController, this)

        addHomeScreen(navController, this)

        addProfileScreen(navController, this)

        addSearchScreen(navController, this)

        addFavoritesScreen(navController, this)

        addHouseDetailsScreen(navController, this)

        addFilterScreen(navController, this)

        addResultScreen(navController, this)

        addQuestionsScreen(navController, this)
    }
}

private fun addLoginScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Login.path) {
        LoginScreen(
            navigateToHome = {
                navController.navigate(NavRoute.Home.path)
            }
        )
    }
}

private fun addHomeScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Home.path) {

        HomeScreen(
            navigateToHouseDetails = { navController.navigate(NavRoute.HouseDetails.path) },
        )
    }
}

private fun addProfileScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Profile.path) {

        ProfileScreen()
    }
}


private fun addSearchScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Search.path) {

        SearchScreen(
            navigateToFilter = { navController.navigate(NavRoute.Filter.path) },
            navigateToHouseDetails = { navController.navigate(NavRoute.HouseDetails.path) }
        )
    }
}

private fun addFavoritesScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Favorite.path) {

        FavoritesScreen(
            navigateToHouseDetails = { navController.navigate(NavRoute.HouseDetails.path) }
        )
    }
}

private fun addHouseDetailsScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.HouseDetails.path) {
        HouseDetailsScreen(
            popBackStack = { navController.popBackStack() })
    }
}

private fun addFilterScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Filter.path) {
        FilterScreen(
            popBackStack = { navController.popBackStack() },
            navigateToResults = { navController.navigate(NavRoute.Results.path) }
        )
    }
}

private fun addResultScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Results.path) {

        ResultScreen(
            navigateToFilter = { navController.navigate(NavRoute.Filter.path) },
            navigateToHouseDetails = { navController.navigate(NavRoute.HouseDetails.path) }
        )
    }
}




private fun addQuestionsScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.Questions.path) {

        QuestionsScreen(
            navigateToHome = {
                navController.navigate(NavRoute.Home.path)
            }
        )
    }
}