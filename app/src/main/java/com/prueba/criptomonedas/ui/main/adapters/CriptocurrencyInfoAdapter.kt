package com.prueba.criptomonedas.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prueba.criptomonedas.data.source.CriptocurrencyInfo
import com.prueba.criptomonedas.databinding.CriptocurrencyInfoBinding

/**
 * Clase que adapta los elementos de la clase CriptocurrencyInfo para
 * ser mostrados en un RecyclerView
 */
class CriptocurrencyInfoAdapter:
    RecyclerView.Adapter<CriptocurrencyInfoAdapter.CriptocurrencyViewHolder>() {

    /**
     * Clase que especifica la forma en que se verá cada CriptocurrencyInfo dentro del RecyclerView
     */
    class CriptocurrencyViewHolder(val criptocurrencyInfoBinding: CriptocurrencyInfoBinding) : RecyclerView.ViewHolder(criptocurrencyInfoBinding.root) {

        /**
         * Agrega un nuevo CriptocurrencyInfo a la vista que debe mostrarlo
         */
        fun bind(criptocurrencyInfo: CriptocurrencyInfo) {
            criptocurrencyInfoBinding.criptocurrencyInfo = criptocurrencyInfo
        }
    }

    /**
     * Lista de CriptocurrencyInfo a mostrar.
     * Se inicializa vacía, posteriormente se actualiza.
     */
    private var criptocurrencyInfoList = emptyList<CriptocurrencyInfo>()

    /**
     * Fuerza una actualización de los CriptocurrencyInfo desde los DataSource definidos
     */
    fun update(criptocurrencyInfoList: List<CriptocurrencyInfo>) {
        this.criptocurrencyInfoList = criptocurrencyInfoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CriptocurrencyViewHolder(
            CriptocurrencyInfoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CriptocurrencyViewHolder, position: Int) {
        holder.bind(criptocurrencyInfoList[position])
    }

    override fun getItemCount(): Int {
        return criptocurrencyInfoList.size
    }
}