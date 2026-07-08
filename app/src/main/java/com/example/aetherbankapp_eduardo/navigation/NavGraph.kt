package com.example.aetherbankapp_eduardo.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aetherbankapp_eduardo.telas.Home
import com.example.aetherbankapp_eduardo.telas.PagarChave
import androidx.compose.animation.core.tween
import androidx.navigation.compose.composable
import com.example.aetherbankapp_eduardo.telas.Pagamento

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Pagamento.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        composable(Routes.Home.route) {
            Home(navController)
        }

        composable(Routes.PagarChave.route) {
            PagarChave(navController)
        }

        composable(Routes.Pagamento.route) {
            Pagamento(navController)
        }
    }
}