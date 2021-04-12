package com.prueba.criptomonedas.data.source

/**
 * Clase que representa la información de una criptomoneda
 */
data class CriptocurrencyInfo(
    /**
     * Código de la criptomoneda
     */
    val code: String,
    /**
     * Nombre de la criptomoneda
     */
    val name: String,
    /**
     * Valor de la criptomoneda
     */
    val value: Double
)