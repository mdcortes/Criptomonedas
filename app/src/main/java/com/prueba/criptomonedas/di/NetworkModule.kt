package com.prueba.criptomonedas.di

import com.google.gson.GsonBuilder
import com.prueba.criptomonedas.data.source.CriptocurrencyInfo
import com.prueba.criptomonedas.data.source.CriptocurrencyInfoDataSource
import com.prueba.criptomonedas.data.source.coinmarketcap.CoinMarketCapDataSource
import com.prueba.criptomonedas.data.source.coinmarketcap.CoinMarketCapDeserializer
import com.prueba.criptomonedas.data.source.coinmarketcap.CoinMarketCapService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Módulo que administra la inyección de dependencias en las clases relacionadas con las operaciones de red
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class NetworkModule {

    /**
     * Provee de una instancia de la interfaz CoinMarketCapService por medio de Retrofit
     */
    @Singleton
    @Provides
    fun providesCoinMarketCapService(): CoinMarketCapService {
        var gsonBuilder = GsonBuilder()
            .registerTypeAdapter(CriptocurrencyInfo::class.java, CoinMarketCapDeserializer())

        return Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/v1")
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()
            .create(CoinMarketCapService::class.java)
    }

    /**
     * Provee de una fuente de datos de tipo CriptocurrencyInfoDataSource a
     */
    @Singleton
    @Binds
    abstract fun bindsCriptocurrencyInfoDataSource(
        coinMarketCapDataSource: CoinMarketCapDataSource
    ): CriptocurrencyInfoDataSource
}