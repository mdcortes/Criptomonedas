package com.prueba.criptomonedas.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.prueba.criptomonedas.data.source.CriptocurrencyInfo
import com.prueba.criptomonedas.data.source.CriptocurrencyInfoRepository
import javax.inject.Inject

/**
 * ViewModel de fragment principal de aplicación
 */
class MainViewModel @Inject constructor(private val criptocurrencyInfoRepository: CriptocurrencyInfoRepository) : ViewModel() {

    /**
     * Objeto LiveData con información de criptomonedas
     */
    private val _criptocurrencyInfoList = MutableLiveData<List<CriptocurrencyInfo>>()
    val criptocurrencyInfoList: LiveData<List<CriptocurrencyInfo>> get() = _criptocurrencyInfoList

    /**
     * Objeto LiveData para mostrar información en SnackBar
     */
    private val _snackbarMessage = MutableLiveData<String>()
    val snackbarMessage: LiveData<String> get() = _snackbarMessage

    init {
        criptocurrencyInfoRepository.observeCriptocurrencyInfo().observeForever(Observer {
            it?: return@Observer

            _criptocurrencyInfoList.value = it
        })
    }

    /**
     * Refresca (actualiza) la lista de criptomonedas
     */
    fun refreshInfoList() {
        criptocurrencyInfoRepository.refreshCriptocurrencyInfo()
    }
}