package ucne.edu.jendri_hidalgo_ap2_p2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object JugadorList : Screen()

    @Serializable
    data class JugadorDetail(val id: Int) : Screen()

    @Serializable
    data class JugadorEdit(val id: Int) : Screen()
}