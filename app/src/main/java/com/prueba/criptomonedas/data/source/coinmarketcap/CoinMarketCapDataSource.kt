package com.prueba.criptomonedas.data.source.coinmarketcap

import com.prueba.criptomonedas.data.source.CriptocurrencyInfo
import com.prueba.criptomonedas.data.source.CriptocurrencyInfoDataSource
import javax.inject.Inject

/**
 * Fuente de datos desde la API de CoinMarketCap, para ser usada por un repositorio
 * @param coinMarketCapService Servicio que provee los datos desde la API
 */
class CoinMarketCapDataSource @Inject constructor(coinMarketCapService: CoinMarketCapService)
    : CriptocurrencyInfoDataSource {

    override fun getCriptocurrencyInfo(): List<CriptocurrencyInfo> {
        TODO("Not yet implemented")
    }
}