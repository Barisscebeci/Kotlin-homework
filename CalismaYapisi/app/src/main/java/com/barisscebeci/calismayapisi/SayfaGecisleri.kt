package com.barisscebeci.calismayapisi

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa") {
        composable("anasayfa") {
            Anasayfa(navController = navController)
        }
        composable(
            "d@etaySayfa/{ad]/{yas}",
            arguments = listOf(
                navArgument("ad"){type = NavType.StringType},
                navArgument("yas"){type = NavType.IntType},
            )
        )
        {
            //if kontolü yapılabilir
            val ad = it.arguments?.getString("ad")!!
            val yas = it.arguments?.getInt("23")
            DetaySayfa(ad, yas!!)
        }
    }
}
