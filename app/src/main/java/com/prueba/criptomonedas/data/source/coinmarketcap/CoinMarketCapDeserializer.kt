package com.prueba.criptomonedas.data.source.coinmarketcap

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.prueba.criptomonedas.data.source.CriptocurrencyInfo
import java.lang.reflect.Type
import java.math.BigDecimal
import kotlin.properties.Delegates

/**
 * Desrializador personalizado para API de CoinMarketCap.
 * Selecciona de la salida de la API solamente los elementos que se van a utilizar en la app
 */
class CoinMarketCapDeserializer : JsonDeserializer<CoinMarketCapResponse> {
    /**
     * Deserializa una lista de CriptocurrencyInfo
     */
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CoinMarketCapResponse {
        val list = mutableListOf<CriptocurrencyInfo>()
        val jsonObject = json!!.asJsonObject

        for (elementJson in jsonObject.entrySet()) {
            if (elementJson.key.equals("data")) {
                for (jsonElement in elementJson.value.asJsonArray)
                list.add(deserializeCriptocurrencyInfo(jsonElement.asJsonObject))
            }
        }

        return CoinMarketCapResponse(list)
    }

    /**
     * Permite deserializar un objeto de tipo CriptocurrencyInfo
     * @param jsonObject JsonObject a deserializar
     * @return Objeto CriptocurrencyInfo deserializado
     */
    private fun deserializeCriptocurrencyInfo(jsonObject : JsonObject) : CriptocurrencyInfo {
        lateinit var code : String
        lateinit var name : String
        lateinit var value : BigDecimal

        for (elementJson in jsonObject.entrySet()) {
            when(elementJson.key) {
                "name" -> name = elementJson.value.asString
                "symbol" -> code = elementJson.value.asString
                "quote" -> {
                    var quote = elementJson.value.asJsonObject
                    value =
                    findCriptocurrencyPrice(quote.entrySet().first().value.asJsonObject)}
            }
        }

        return CriptocurrencyInfo(code, name, value)
    }

    /**
     * Encuentra el precio de una criptomoneda dentro de un quote en la llamada
     * @param jsonObject jsonObject en el que se busca el precio
     * @return Precio de la criptomoneda
     */
    private fun findCriptocurrencyPrice(jsonObject: JsonObject) : BigDecimal {
        for(elementJson in jsonObject.entrySet()) {
            if (elementJson.key.equals("price")) {
                return elementJson.value.asBigDecimal
            }
        }

        return BigDecimal.ZERO
    }
}