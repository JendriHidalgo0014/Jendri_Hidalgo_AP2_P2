package ucne.edu.jendri_hidalgo_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.details.DetailJugadorScreen
import ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.list.ListJugadorScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.JugadorList
    ) {
        composable<Screen.JugadorList> {
            ListJugadorScreen(
                onJugadorClick = { Id ->
                    navHostController.navigate(Screen.JugadorDetail(Id))
                }
            )
        }

        composable<Screen.JugadorDetail> {
            DetailJugadorScreen(
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}