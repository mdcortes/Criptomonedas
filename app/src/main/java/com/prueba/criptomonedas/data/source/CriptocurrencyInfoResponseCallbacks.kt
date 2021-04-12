package com.prueba.criptomonedas.data.source

/**
 * Interfaz de callbacks para el manejo de las respuestas desde los data sources
 */
interface CriptocurrencyInfoResponseCallbacks {

    /**
     * Callback a ser ejecutado cuando la llamada tiene éxito
     * @param response Respuesta con los datos requeridos
     */
    fun onSuccess(response: List<CriptocurrencyInfo>)

    /**
     * Callback a ser ejecutado cuando la llamada falla
     */
    fun onError(message: String)
}