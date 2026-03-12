package ucne.edu.jendri_hidalgo_ap2_p2.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.JugadorRemoteDataSource
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.Resource
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.dto.JugadorRequest
import ucne.edu.jendri_hidalgo_ap2_p2.domain.model.Jugador
import ucne.edu.jendri_hidalgo_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class JugadorRepositoryImpl @Inject constructor(
    private val remoteDataSource: JugadorRemoteDataSource
) : JugadorRepository {

    override fun getJugadores(
        nombres: String?,
        email: String?
    ): Flow<Resource<List<Jugador>>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getJugadores(nombres, email)
        response.onSuccess { jugadores ->
            emit(Resource.Success(jugadores.map { it.toDomain() }))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun getJugadorDetail(id: Int): Flow<Resource<Jugador>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getJugadorDetail(id)
        response.onSuccess { jugador ->
            emit(Resource.Success(jugador.toDomain()))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun createJugador(nombres: String, email: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.createJugador(JugadorRequest(nombres, email))
        response.onSuccess {
            emit(Resource.Success(Unit))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun updateJugador(id: Int, nombres: String, email: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.updateJugador(id, JugadorRequest(nombres, email))
        response.onSuccess {
            emit(Resource.Success(Unit))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun saveJugador(id: Int?, nombres: String, email: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val request = JugadorRequest(nombres, email)
        val response = remoteDataSource.saveJugador(id, request)
        response.onSuccess {
            emit(Resource.Success(Unit))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }
}