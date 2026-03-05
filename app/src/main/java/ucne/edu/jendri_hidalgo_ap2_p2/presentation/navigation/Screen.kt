package ucne.edu.jendri_hidalgo_ap2_p2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen{
    @Serializable
    data object BorrameList : Screen()

    @Serializable
    data class BorrameDetail(val id: Int) : Screen()
}