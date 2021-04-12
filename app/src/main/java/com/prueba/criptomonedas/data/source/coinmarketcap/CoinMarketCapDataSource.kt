package com.prueba.criptomonedas.data.source.coinmarketcap

import android.content.Context
import com.prueba.criptomonedas.R
import com.prueba.criptomonedas.data.source.CriptocurrencyInfo
import com.prueba.criptomonedas.data.source.CriptocurrencyInfoDataSource
import com.prueba.criptomonedas.data.source.CriptocurrencyInfoResponseCallbacks
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Fuente de datos desde la API de CoinMarketCap, para ser usada por un repositorio
 * @param context Contexto de la aplicación
 * @param coinMarketCapService Servicio que provee los datos desde la API
 */
class CoinMarketCapDataSource @Inject constructor(@ApplicationContext val context: Context,
                                                  private val coinMarketCapService: CoinMarketCapService)
    : CriptocurrencyInfoDataSource {

    override fun getCriptocurrencyInfo(responseCallbacks: CriptocurrencyInfoResponseCallbacks) {
        coinMarketCapService.getLatestListingCriptocurrencies(
            context.getString(R.string.coinmarketcap_api_key), "CLP", 10, "price"
        ).enqueue(object : Callback<CoinMarketCapResponse> {
            override fun onResponse(
                call: Call<CoinMarketCapResponse>,
                response: Response<CoinMarketCapResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let { responseCallbacks.onSuccess(it.data) }
                }

                else {
                    responseCallbacks.onError(response.message())
                }
            }

            override fun onFailure(call: Call<CoinMarketCapResponse>, t: Throwable) {
                t.message?.let { responseCallbacks.onError(it) }
            }

        })
    }
}