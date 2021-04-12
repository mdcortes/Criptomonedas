package com.prueba.criptomonedas.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.prueba.criptomonedas.databinding.MainFragmentBinding
import com.prueba.criptomonedas.ui.main.adapters.CriptocurrencyInfoAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Fragment principal de la aplicación
 */
@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    /**
     * ViewModel que que actúa de intermediario entre la vista y el modelo.
     */
    @Inject
    lateinit var viewModel: MainViewModel

    /**
     * DataBinding de la vista de este fragment
     */
    private lateinit var mainFragmentBinding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflamos una vista con DataBinding para este fragment
        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)

        mainFragmentBinding.lifecycleOwner = viewLifecycleOwner
        return mainFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Configuramos el RecyclerView y su Adapter
        mainFragmentBinding.criptocurrencyInfoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CriptocurrencyInfoAdapter()
        }

        viewModel.criptocurrencyInfoList.observe(viewLifecycleOwner,
        Observer {
            it?: return@Observer

            (mainFragmentBinding.criptocurrencyInfoRecyclerView.adapter
                    as CriptocurrencyInfoAdapter).update(it)
        })

        viewModel.refreshInfoList()
    }

}