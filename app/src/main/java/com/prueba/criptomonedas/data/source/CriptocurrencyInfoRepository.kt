package com.prueba.criptomonedas.data.source

import androidx.lifecycle.LiveData

/**
 * Interfaz para repositorio de información de criptomonedas
 */
interface CriptocurrencyInfoRepository {

    /**
     * Permite observar los cambios en la información de criptomonedas disponible
     */
    fun observeCriptocurrencyInfo(): LiveData<List<CriptocurrencyInfo>>

    /**
     * Permite observar mesajes de error en el repositorio
     */
    fun observeErrorMessages(): LiveData<String>

    /**
     * Permite refrescar la información de criptomonedas disponible
     */
    fun refreshCriptocurrencyInfo()
}