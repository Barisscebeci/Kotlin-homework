package com.barisscebeci.todoapplication.uix.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.barisscebeci.todoapplication.data.entity.ToDo
import com.barisscebeci.todoapplication.uix.view.HomeScreen
import com.barisscebeci.todoapplication.uix.view.NoteCreationPage
import com.barisscebeci.todoapplication.uix.view.NoteDetailsPage
import com.barisscebeci.todoapplication.uix.viewmodel.HomeScreenViewModel
import com.barisscebeci.todoapplication.uix.viewmodel.NoteCreationViewModel
import com.barisscebeci.todoapplication.uix.viewmodel.NoteDetailsViewModel
import com.google.gson.Gson

@Composable
fun PageTransitions(
    homeScreenViewModel: HomeScreenViewModel,
    noteCreationViewModel: NoteCreationViewModel,
    noteDetailsViewModel: NoteDetailsViewModel,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") {
            HomeScreen(navController, homeScreenViewModel)
        }

        composable("noteCreationScreen") {
            NoteCreationPage(navController = navController, noteCreationViewModel = noteCreationViewModel)
        }

        composable(
            "noteDetailsScreen/{todos}",
            arguments = listOf(
                navArgument("todos") {
                    type = NavType.StringType
                }
            )
        ) {

            val json = it.arguments?.getString("todos")
            val todosObject = Gson().fromJson(json, ToDo::class.java)


            NoteDetailsPage(navController = navController, noteDetailsViewModel = noteDetailsViewModel, todosObj = todosObject)
        }

    }
}

