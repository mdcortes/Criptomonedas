package com.prueba.criptomonedas.data.source.coinmarketcap

import com.prueba.criptomonedas.data.source.CriptocurrencyInfo
import retrofit2.Call
import retrofit2.http.*

/**
 * Interfaz que define la comunicación con la API de CoinMarketCap
 */
interface CoinMarketCapService {

    /**
     * Obtiene la última información disponible sobre criptomonedas
     * @param apiKey API key para realizar la llamada
     * @param convert Código de tipo de cambio en el que se espera el resultado (por ejemplo,
     * CLP para peso chileno)
     * @param limit Cantidad de elementos que se requerirán como máximo
     * @param sort Parámetro por el que se ordenarán los resultados de la llamada
     */
    @Headers("Accept: application/json")
    @GET("cryptocurrency/listings/latest")
    fun getLatestListingCriptocurrencies(
        @Header("X-CMC_PRO_API_KEY") apiKey: String,
        @Query("convert") convert: String,
        @Query("limit") limit: Int,
        @Query("sort") sort: String
    ): Call<CoinMarketCapResponse>
}