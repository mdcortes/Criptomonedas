package com.prueba.criptomonedas.di

import com.prueba.criptomonedas.data.source.CriptocurrencyInfoDataSource
import com.prueba.criptomonedas.data.source.CriptocurrencyInfoRepository
import com.prueba.criptomonedas.data.source.DefaultCriptocurrencyInfoRepository
import com.prueba.criptomonedas.data.source.coinmarketcap.CoinMarketCapDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Módulo que administra la inyección de dependencias en las clases relacionadas con
 * las operaciones de red
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    /**
     * Provee de una fuente de datos de tipo CriptocurrencyInfoDataSource a
     */
    @Singleton
    @Binds
    abstract fun bindsCriptocurrencyInfoDataSource(
        coinMarketCapDataSource: CoinMarketCapDataSource
    ): CriptocurrencyInfoDataSource

    /**
     * Provee de un repositorio de CriptocurrencyInfo
     */
    @Singleton
    @Binds
    abstract fun bindsCriptocurrencyInfoRepository(
        defaultCriptocurrencyInfoRepository: DefaultCriptocurrencyInfoRepository
    ): CriptocurrencyInfoRepository
}