package com.prueba.criptomonedas.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

/**
 * Repositorio de información de criptomonedas por defecto
 */
class DefaultCriptocurrencyInfoRepository @Inject constructor(var dataSource: CriptocurrencyInfoDataSource): CriptocurrencyInfoRepository {

    /**
     * Objeto LiveData con información de criptomonedas
     */
    private val _criptocurrencyInfo = MutableLiveData<List<CriptocurrencyInfo>>()

    /**
     * Objeto LiveData usado para mostrar mensajes de error
     */
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    override fun observeCriptocurrencyInfo(): LiveData<List<CriptocurrencyInfo>> {
        return _criptocurrencyInfo
    }

    override fun refreshCriptocurrencyInfo() {
        dataSource.getCriptocurrencyInfo(object: CriptocurrencyInfoResponseCallbacks {
            override fun onSuccess(response: List<CriptocurrencyInfo>) {
                _criptocurrencyInfo.value = response
            }

            override fun onError(message: String) {
                _errorMessage.value = message
            }
        })
    }
}