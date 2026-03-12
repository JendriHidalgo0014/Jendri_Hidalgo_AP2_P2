package ucne.edu.jendri_hidalgo_ap2_p2.data.remote

import retrofit2.HttpException
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.dto.*
import javax.inject.Inject

class JugadorRemoteDataSource @Inject constructor(
    private val api: JugadoresApi
) {

    suspend fun getJugadores(nombres: String?, email: String?): Result<List<JugadorDto>> {
        try {
            val response = api.getJugadores(nombres, email)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getJugadorDetail(id: Int): Result<JugadorDto> {
        try {
            val response = api.getJugadorDetail(id)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun createJugador(request: JugadorRequest): Result<JugadorResponse> {
        try {
            val response = api.createJugador(request)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}: ${response.errorBody()?.string()}"))
            }
            val body = response.body()
            return if (body != null) {
                Result.success(body)
            } else {
                Result.success(JugadorResponse(0, request.nombres, request.email))
            }
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor: ${e.message}"))
        } catch (e: Exception) {
            return Result.failure(Exception("Error: ${e.message}"))
        }
    }

    suspend fun updateJugador(id: Int, request: JugadorRequest): Result<JugadorResponse> {
        try {
            val response = api.updateJugador(id, request)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun saveJugador(id: Int?, request: JugadorRequest): Result<JugadorResponse> {
        return if (id == null || id == 0) {
            createJugador(request)
        } else {
            updateJugador(id, request)
        }
    }
}