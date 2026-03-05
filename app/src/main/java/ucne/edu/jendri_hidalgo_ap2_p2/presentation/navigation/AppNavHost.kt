package ucne.edu.jendri_hidalgo_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.details.DetailBorrameScreen
import ucne.edu.jendri_hidalgo_ap2_p2.presentation.tareas.list.ListBorrameScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.BorrameList
    ) {
        composable<Screen.BorrameList> {
            ListBorrameScreen(
                onBorrameClick = { Id ->
                    navHostController.navigate(Screen.BorrameDetail(Id))
                }
            )
        }

        composable<Screen.BorrameDetail> {
            DetailBorrameScreen(
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}