package com.example.rickandmortycompose.data

import com.example.rickandmortycompose.BuildConfig.BASE_URL
import com.example.rickandmortycompose.data.network.api.RickAndMortyApiService
import com.example.rickandmortycompose.data.repository.CharactersRepository
import com.example.rickandmortycompose.data.repository.EpisodeRepository
import com.example.rickandmortycompose.data.repository.SingleCharacterRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val dataModule: Module = module {

    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }   // get() будет искать okhttpclient в модуле, и вернет верхнюю provideOkHttpClient()
    single { get<Retrofit>().create(RickAndMortyApiService::class.java) }  // get() будет искать retrofit в модуле, и вернет верхнюю provideRetrofit()
    single { CharactersRepository(get()) }   // get() будет искать CharacterApiService в модуле
    single { EpisodeRepository(get()) }
    single { SingleCharacterRepository(get()) }

}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).client(okHttpClient)
        .build()
}
