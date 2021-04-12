package com.prueba.criptomonedas.data.source

/**
 * Interfaz para las fuentes de datos de información sobre criptomonedas
 */
interface CriptocurrencyInfoDataSource {
    /**
     * Obtiene información actualizada sobre las criptomonedas
     */
    fun getCriptocurrencyInfo() : List<CriptocurrencyInfo>
}