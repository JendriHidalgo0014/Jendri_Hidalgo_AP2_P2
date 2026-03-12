package ucne.edu.jendri_hidalgo_ap2_p2.domain.repository

import kotlinx.coroutines.flow.Flow
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.Resource
import ucne.edu.jendri_hidalgo_ap2_p2.domain.model.Jugador

interface JugadorRepository {
    fun getJugadores(nombres: String?, email: String?): Flow<Resource<List<Jugador>>>
    fun getJugadorDetail(id: Int): Flow<Resource<Jugador>>
    fun createJugador(nombres: String, email: String): Flow<Resource<Unit>>
    fun updateJugador(id: Int, nombres: String, email: String): Flow<Resource<Unit>>
    fun saveJugador(id: Int?, nombres: String, email: String): Flow<Resource<Unit>>
}