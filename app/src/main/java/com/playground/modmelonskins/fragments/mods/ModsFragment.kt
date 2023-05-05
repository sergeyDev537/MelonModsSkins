package com.playground.modmelonskins.fragments.mods

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.playground.modmelonskins.databinding.FragmentModsBinding
import com.playground.modmelonskins.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModsFragment : BaseFragment<FragmentModsBinding>(FragmentModsBinding::inflate) {

    private val modsViewModel: ModsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        modsViewModel.apply {
            setObservable()
        }
    }

    private fun ModsViewModel.setObservable() {
        listMods.observe(viewLifecycleOwner){
            Log.d("TAGING", "LIST MODS FRAGMENT SIZE: ${it.size}")
        }
    }
}