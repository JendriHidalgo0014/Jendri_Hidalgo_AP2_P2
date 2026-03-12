package ucne.edu.jendri_hidalgo_ap2_p2.data.remote

import retrofit2.Response
import retrofit2.http.*
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.dto.*

interface JugadoresApi {

    @GET("api/Jugadores")
    suspend fun getJugadores(
        @Query("nombres") nombres: String?,
        @Query("email") email: String?,
    ): Response<JugadorResponseDto>

    @GET("api/Jugadores/{id}")
    suspend fun getJugadorDetail(
        @Path("id") id: Int
    ): Response<JugadorDto>

    @POST("api/Jugadores")
    suspend fun createJugador(
        @Body request: JugadorRequest
    ): Response<JugadorResponse>

    @PUT("api/Jugadores/{id}")
    suspend fun updateJugador(
        @Path("id") id: Int,
        @Body request: JugadorRequest
    ): Response<JugadorResponse>
}
