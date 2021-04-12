package com.prueba.criptomonedas.di

import com.google.gson.GsonBuilder
import com.prueba.criptomonedas.data.source.coinmarketcap.CoinMarketCapDeserializer
import com.prueba.criptomonedas.data.source.coinmarketcap.CoinMarketCapResponse
import com.prueba.criptomonedas.data.source.coinmarketcap.CoinMarketCapService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Módulo que maneja la inyección de dependencias con Hilt de clases relacionadas con Retrofit
 */
@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    /**
     * Provee de una instancia de la interfaz CoinMarketCapService por medio de Retrofit
     */
    @Singleton
    @Provides
    fun providesCoinMarketCapService(): CoinMarketCapService {
        var gsonBuilder = GsonBuilder()
            .registerTypeAdapter(CoinMarketCapResponse::class.java, CoinMarketCapDeserializer())

        return Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()
            .create(CoinMarketCapService::class.java)
    }
}