package com.prueba.criptomonedas.data.source

/**
 * Interfaz para las fuentes de datos de información sobre criptomonedas
 */
interface CriptocurrencyInfoDataSource {

    /**
     * Obtiene información actualizada sobre las criptomonedas
     * @param responseCallbacks Callbacks a ser llamados dependiendo del resultado de la solicitud
     */
    fun getCriptocurrencyInfo(responseCallbacks: CriptocurrencyInfoResponseCallbacks)
}