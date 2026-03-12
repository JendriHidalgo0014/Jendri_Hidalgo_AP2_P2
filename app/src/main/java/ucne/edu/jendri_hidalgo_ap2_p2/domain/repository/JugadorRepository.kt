package ucne.edu.jendri_hidalgo_ap2_p2.domain.repository

import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.Resource
import ucne.edu.jendri_hidalgo_ap2_p2.domain.model.Jugador


interface JugadorRepository {
    suspend fun getJugadores(
        nombres: String?,
        email: String?
    ): Resource<List<Jugador>>

    suspend fun getJugadorDetail(id: Int): Resource<Jugador>
}