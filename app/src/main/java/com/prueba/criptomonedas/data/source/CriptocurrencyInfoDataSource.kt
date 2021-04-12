package com.prueba.criptomonedas.data.source

interface CriptocurrencyInfoDataSource {
    fun getCriptocurrencyInfo() : List<CriptocurrencyInfo>
}