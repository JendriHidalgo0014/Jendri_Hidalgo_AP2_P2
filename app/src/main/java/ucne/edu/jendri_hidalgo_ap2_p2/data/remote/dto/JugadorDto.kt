package ucne.edu.jendri_hidalgo_ap2_p2.data.remote.dto

import ucne.edu.jendri_hidalgo_ap2_p2.domain.model.Jugador

data class JugadorDto(
    val jugadorId: Int,
    val nombres: String,
    val email: String,
) {
    fun toDomain() = Jugador(jugadorId, nombres, email)
}



