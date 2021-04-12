package com.prueba.criptomonedas.data.source.coinmarketcap

import com.prueba.criptomonedas.data.source.CriptocurrencyInfo

/**
 * Clase que representa la respuesta de la API de CoinMarketCap
 */
data class CoinMarketCapResponse (
    /**
     * Lista con la información de las criptomonedas requerida
     */
    var data: List<CriptocurrencyInfo>
    )