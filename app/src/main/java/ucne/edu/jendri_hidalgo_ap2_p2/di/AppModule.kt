package ucne.edu.jendri_hidalgo_ap2_p2.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.JugadorRemoteDataSource
import ucne.edu.jendri_hidalgo_ap2_p2.data.remote.JugadoresApi
import ucne.edu.jendri_hidalgo_ap2_p2.data.repository.JugadorRepositoryImpl
import ucne.edu.jendri_hidalgo_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): JugadoresApi {
        return Retrofit.Builder()
            .baseUrl("https://gestionhuacalesapi.azurewebsites.net/swagger/index.html")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(JugadoresApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideJugadorRepository(remoteDataSource: JugadorRemoteDataSource): JugadorRepository {
        return JugadorRepositoryImpl(remoteDataSource)
    }
}