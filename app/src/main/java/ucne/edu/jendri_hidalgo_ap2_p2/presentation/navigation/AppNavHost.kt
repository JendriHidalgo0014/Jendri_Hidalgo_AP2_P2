package ucne.edu.jendri_hidalgo_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.details.DetailJugadorScreen
import ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.edit.EditJugadorScreen
import ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.list.ListJugadorScreen

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.JugadorList
    ) {
        composable<Screen.JugadorList> {
            ListJugadorScreen(
                onJugadorClick = { id ->
                    navHostController.navigate(Screen.JugadorDetail(id))
                },
                onAddClick = {
                    navHostController.navigate(Screen.JugadorEdit(0))
                }
            )
        }

        composable<Screen.JugadorDetail> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.JugadorDetail>()
            DetailJugadorScreen(
                onBack = { navHostController.navigateUp() },
                onEdit = { navHostController.navigate(Screen.JugadorEdit(args.id)) }
            )
        }

        composable<Screen.JugadorEdit> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.JugadorEdit>()
            EditJugadorScreen(
                jugadorId = args.id,
                goBack = { navHostController.navigateUp() }
            )
        }
    }
}