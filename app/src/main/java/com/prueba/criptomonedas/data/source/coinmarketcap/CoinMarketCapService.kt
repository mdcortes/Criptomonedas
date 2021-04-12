package com.prueba.criptomonedas.data.source.coinmarketcap

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface CoinMarketCapService {

    @Headers("Accept: application/json")
    @GET("cryptocurrency/listings/latest?limit={quantity}&sort=price")
    fun getLatestListingCriptocurrencies(@Header("X-CMC_PRO_API_KEY") api_key: String, @Path("quantity") quantity: Int): Call<Object>
}